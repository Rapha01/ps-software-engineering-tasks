

package ps_se_ws2015.hattinger;

import java.io.PrintStream;
import java.lang.reflect.*;
import java.util.HashSet;
import java.util.Set;



public class Reconstructor {



    private String classPackage = "";
    private Set<String> imports = new HashSet<>();
    private Class<?> c_lass;
    private StringBuffer strbuilder = new StringBuffer();

	/**
	 *@author Michael Pingert
	 *@author Sebastian Hartinger
	 *@version V1.0
	 *
	 * The Reconstructor reconstruct  a class and generates the code
	 * in a readable format. The Reconstructor takes care about
	 *   -modifiers (public, private,...)
	 *   -inheritances (extends, implements)
	 *   -Exceptions (throw IOExceptions)
	 *   -Arrays
	 *
	 *
	 */


	/**
	 * basisconstuctor for Reconstructionclass
	 */
    public Reconstructor() {
    }

	/**
	 * blabla
	 * @param c: Class to reconstruct
	 * @param ps: Printstream to print the reconsttructed code
	 */
	public void reconstruct(Class c, PrintStream ps) {
		c_lass= c;
		ps.println(classCompiler());
		ps.close();
	}

	public void reconstruct(String fullClassname, PrintStream ps) throws ClassNotFoundException {
		c_lass = Class.forName(fullClassname);
		ps.println(classCompiler());
		ps.close();
	}


    public String classCompiler() {

        Header();
        Fields();
        Constructors();
        Methods();
        createImports();
        strbuilder.append("}");
        return strbuilder.toString();
    }

    private String solveEntry (String simple, String full) {
        if (full.contains("java") && !full.contains("java.lang")
                && !(full.contains(classPackage) && classPackage.contains("java"))) {
            imports.add(full.indexOf("[") >= 0 ? full.substring(0, full.indexOf("[")) : full);
        }
        return simple;

    }

    private void createParameter(Class<?> par[]) {

        for (int i = 0; i < par.length; i++) {
            strbuilder.append(solveEntry(par[i].getSimpleName(), par[i].getCanonicalName()));
            strbuilder.append(" param" + i);
            if (i < par.length - 1) strbuilder.append(", ");
        }
    }

    private void Header() {
        boolean isInterface = c_lass.isInterface();
        boolean isEnum = c_lass.isEnum();


        if (c_lass.getPackage() != null) classPackage = c_lass.getPackage().getName();
        strbuilder.append(Modifier.toString(c_lass.getModifiers()) + " ");
        if (!(isEnum || isInterface)) strbuilder.append("class");
        strbuilder.append(c_lass.getSimpleName() + " ");

        if (c_lass.getSuperclass() != null && !c_lass.getSuperclass().getName().contains("Object")) {
            strbuilder.append(" extends ");
            strbuilder.append(solveEntry(c_lass.getSuperclass().getSimpleName(), c_lass.getSuperclass().getName()));
        }


        Class<?> interfaces[] = c_lass.getInterfaces();
        if (interfaces.length != 0) {
            strbuilder.append(" implements ");
            for (int i = 0; i < interfaces.length; i++) {
                strbuilder.append(solveEntry(interfaces[i].getSimpleName(), interfaces[i].getName()));
                if (i < interfaces.length - 1) strbuilder.append(", ");
            }
        }
        strbuilder.append(" { \n\n");
    }

    private void Fields() {
        Field[] fields = c_lass.getDeclaredFields();

        for (Field fieldlist : fields) {
            strbuilder.append("     " + Modifier.toString(fieldlist.getModifiers()));
            strbuilder.append(" " + solveEntry(fieldlist.getType().getSimpleName(), fieldlist.getType().getName()));


            strbuilder.append(" " + fieldlist.getName());

            if (!fieldlist.getType().getSimpleName().equals("void")) {
                if (fieldlist.getType().isPrimitive()) {
                    if (fieldlist.getType().getSimpleName().equals("boolean")) strbuilder.append(" = false;\n");
                    else strbuilder.append(" = 0;\n");
                } else strbuilder.append(" = null;\n");
            }
        }
        strbuilder.append("\n");
    }

    private void Constructors() {
        for (Constructor<?> listConstructor : c_lass.getDeclaredConstructors()) {
            strbuilder.append("     " + Modifier.toString(listConstructor.getModifiers()) + " ");
            strbuilder.append(listConstructor.getName().substring(classPackage.length() > 0 ? classPackage.length() + 1 : 0,
                    listConstructor.getName().length()) + "Dummy (");
            createParameter(listConstructor.getParameterTypes());
            strbuilder.append(") {\n        System.out.println(\"Constructor\");\n" + "     }\n\n");

        }
    }

    private void Methods() {
        Method methods[] = c_lass.getDeclaredMethods();
        for (Method listmethodes : methods) {
            strbuilder.append("     " + Modifier.toString(listmethodes.getModifiers()) + " ");
            strbuilder.append(solveEntry(listmethodes.getReturnType().getSimpleName(), listmethodes.getReturnType()
                    .getSimpleName()));

            strbuilder.append(" " + listmethodes.getName() + "(");
            createParameter(listmethodes.getParameterTypes());
            strbuilder.append(")");

            Class<?> excepts[] = listmethodes.getExceptionTypes();
            if (excepts.length > 0) {
                strbuilder.append(" throws ");
                for (int j = 0; j < excepts.length; j++) {
                    strbuilder.append(solveEntry(excepts[j].getSimpleName(), excepts[j].getName()));
                    if (j < excepts.length - 1) strbuilder.append(", ");
                }
            }

            if (c_lass.isInterface()) strbuilder.append(";\n\n");
            else {
                strbuilder.append(" {\n        System.out.println(\"" + listmethodes.getName() + "\");\n");
                if (!listmethodes.getReturnType().getSimpleName().equals("void")) {
                    strbuilder.append("        return ");
                    if (listmethodes.getReturnType().isPrimitive()) {
                        if (listmethodes.getReturnType().getSimpleName().equals("boolean"))
                            strbuilder.append(" false;\n");
                        else strbuilder.append(" 0;\n");
                    } else strbuilder.append(" null;\n");
                }
                strbuilder.append("    }\n\n");

            }

        }
    }

    private void createImports() {
        strbuilder.insert(0, "\n");
        for (String imp : imports) {
            strbuilder.insert(0, "import " + imp + ";\n");
        }
        if (!classPackage.equals("")) {
            strbuilder.insert(0, "package " + classPackage + ";\n\n");
        }

    }
}
