package usingclassobject;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Scanner;
import java.lang.*;
import java.util.*;

/**
 *
 * @author jose
 */
public class UsingClassObject {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        // Variable para recoger datos por teclado
        Scanner sc = new Scanner(System.in);
        
        // Variable que contendrá el nombre de la clase
        String name;
        
        // Variable que contendra la referencia a la entidad
        Class<?> clase;
        
        // Variable que controla la salida del bucle
        boolean exit = false;
        
        while(!exit)
        {
            // Pedimos nombre de la clase. Ejemplo: java.lang.String
            System.out.print("Introduzca el nombre de la clase: ");
            name = sc.nextLine();
            
            // Separacion
            System.out.println();
            
            try
            {
                clase = Class.forName(name);
                
                // Cabecera
                System.out.println("Informacion de la clase " + clase.getName() + " [getName()]. " + clase.getCanonicalName() + " [getCanonicalName()]\n");
                
                // ¿Que es la clase?
                if(clase.isAnnotation())
                    System.out.println("La clase es un objeto que representa un tipo Annotation");
                
                if(clase.isAnonymousClass())
                    System.out.println("La clase es anonima");
                
                if(clase.isInterface())
                    System.out.println("La clase es una interfaz");
                
                if(clase.isLocalClass())
                    System.out.println("La clase es una clase local");
                
                if(clase.isMemberClass())
                    System.out.println("La clase es una clase miembro");
                
                if(clase.isPrimitive())
                    System.out.println("La clase representa un tipo primitivo");
                
                if(clase.isSynthetic())
                    System.out.println("La clase es una clase sintetica");
                
                // Modificadores
                System.out.println("La clase tiene los siguientes modificadores (representado en un entero): " + clase.getModifiers() + "\n");
                
                // Paquete
                System.out.println("La clase pertenece al paquete: " + clase.getPackage() + "\n");
                
                // Informacion de campos
                System.out.println("La clase tiene los siguientes campos: \n");
                if(clase.getFields().length != 0)
                {
                    for(Field f: clase.getFields())
                    {
                        System.out.println("\tNombre del campo: " + f.getName() + "\n" +
                                "\tTipo del campo: " + f.getType() + "\n" +
                                "\tDescripcion generica del campo: " + f.toGenericString() + "\n" +
                                "\tDescripcion del campo: " + f.toString() + "\n");
                    }
                }
                else
                {
                    System.out.println("\tLa clase no posee campos publicos, y por lo tanto no se puede acceder a ellos.");
                }
                
                // Informacion de los constructores
                System.out.println("La clase tiene el/los sieguiente/s constructores: \n");
                if(clase.getConstructors().length != 0)
                {
                    for(Constructor<?> c: clase.getConstructors())
                    {
                        System.out.println("\tNombre del constructor: " + c.getName() + "\n" +
                                "\tNumero de parametros: " + c.getParameterCount() + "\n" +
                                "\tTipos de los parametros: ");
                        
                        if(c.getParameterCount() > 0)
                        {
                            for(Class<?> cl: c.getParameterTypes())
                            {
                                System.out.println("\t\t" + cl.getTypeName());
                            }
                        }
                        else
                        {
                            System.out.println("\t\tEl constructor no tiene parametros");
                        }
                        
                        System.out.println("\tDescripcion generica: " + c.toGenericString() + "\n" +
                                "\tDescripcion: " + c.toString() + "\n");
                    }
                }
                else
                {
                    System.out.println("\tLa clase no posee constructores.");
                }
                
                // Informacion de metodos
                System.out.println("La clase tiene el/los sieguiente/s metodos: \n");
                if(clase.getMethods().length != 0) // Comprobacion sin sentido, todos los objetos tiene al menos un metodo heredado de Object en ultima instancia
                {
                    for(Method m: clase.getMethods())
                    {
                        System.out.println("\tNombre del metodo: " + m.getName() + "\n" +
                                "\tNumero de parametros: " + m.getParameterCount() + "\n" + 
                                (m.isBridge()? "\tEl metodo es un metodo bridge\n" : "") +
                                (m.isDefault() ? "\tEl metodo es un metodo por defecto\n" : "") +
                                (m.isSynthetic() ? "\tEl metodo es sintetico\n" : "") +
                                (m.isVarArgs() ? "\tEl numero de argumentos del metodo es variable\n" : "") +
                                "\tDescripcion generica del metodo: " + m.toGenericString() + "\n" + 
                                "\tDescripcion del metodo: " + m.toString() + "\n");
                    }
                }
                else
                {
                    System.out.println("\tLa clase no tiene metodos");
                }
            }
            catch(ClassNotFoundException cnfex)
            {
                System.out.println("La clase introducida no existe");
            }
            catch(LinkageError le)
            {
                System.out.println("Error de linkado");
            }
            catch(Exception error)
            {
                System.out.print("Ha ocurrido un error inesperado, presione enter para continuar");
                sc.nextLine();
            }
            
            System.out.print("\n¿Desea ver la informacion de otra clase? (Si para confirmar): ");
            name = sc.nextLine();
            
            if(!name.equalsIgnoreCase("si"))
                exit = true;
        }
    }
}
