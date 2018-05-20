/*
 * Copyright 2017 Rawand Kurdy 
 * Please dont reuse or change anything in this materials unless you got permissions from the coder ##RKY 
 * If you find any violation , please pay a visit to our site and contact us //www.rky.cu.cc
 */
package resources;

/**
 *
 * @author mkyong // https://www.mkyong.com/java/how-to-detect-os-in-java-systemgetpropertyosname/
 * References

    os.name value – http://lopica.sourceforge.net/os.html
    System.getProperty() – http://java.sun.com/j2se/1.4.2/docs/api/java/lang/System.html

 * 
 */
public class OSValidator {

	private static String OS = System.getProperty("os.name").toLowerCase();

	public static boolean isWindows() {

		return (OS.indexOf("win") >= 0);

	}

	public static boolean isMac() {

		return (OS.indexOf("mac") >= 0);

	}

	public static boolean isUnix() {

		return (OS.indexOf("nix") >= 0 || OS.indexOf("nux") >= 0 || OS.indexOf("aix") > 0 );
		
	}

	public static boolean isSolaris() {

		return (OS.indexOf("sunos") >= 0);

	}

}
