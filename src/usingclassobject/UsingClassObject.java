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
                // Obtenemos el objeto Class que referencia la clase introducida como cadena
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
                    // Para cada campo contenido en el array que devuelve el metodo getFields()
                    for(Field f: clase.getFields())
                    {
                        // Mostramos nombre, tipo, descripcion generica y descripcion normal
                        System.out.println("\tNombre del campo: " + f.getName() + "\n" +
                                "\tTipo del campo: " + f.getType() + "\n" +
                                "\tDescripcion generica del campo: " + f.toGenericString() + "\n" +
                                "\tDescripcion del campo: " + f.toString() + "\n");
                    }
                }
                else
                {
                    // Se informa si no hay campos publicos accesibles
                    System.out.println("\tLa clase no posee campos publicos, y por lo tanto no se puede acceder a ellos.");
                }
                
                // Informacion de los constructores
                System.out.println("La clase tiene el/los sieguiente/s constructores: \n");
                if(clase.getConstructors().length != 0)
                {
                    // Para cada constructor que posea la clase
                    for(Constructor<?> c: clase.getConstructors())
                    {
                        // Mostramos nombre, numero de parametros, tipo de parametros...
                        System.out.println("\tNombre del constructor: " + c.getName() + "\n" +
                                "\tNumero de parametros: " + c.getParameterCount() + "\n" +
                                "\tTipos de los parametros: ");
                        
                        // Aqui obtenemos el listado de parametros en un array que recorremos
                        if(c.getParameterCount() > 0)
                        {
                            for(Class<?> cl: c.getParameterTypes())
                            {
                                System.out.println("\t\t" + cl.getTypeName());
                            }
                        }
                        else
                        {
                            // Aviso si no hay parametros
                            System.out.println("\t\tEl constructor no tiene parametros");
                        }
                        
                        // ... descripcion generica, y normal
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
                    // Para cada metodo de la clase
                    for(Method m: clase.getMethods())
                    {
                        // Mostramos nombre, numero de parametros, si es un metodo bridge,
                        // por defecto, sintetico, con argumentos variables, descripcion generica
                        // y descripcion normal.
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
                    // Aviso si no hay metodos (Se supone imposible, siempre se hereda algun metodo de Object
                    System.out.println("\tLa clase no tiene metodos");
                }
            }// Tratamiento de excepciones
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
            
            // Pregunta para salir del programa
            System.out.print("\n¿Desea ver la informacion de otra clase? (Si para confirmar): ");
            name = sc.nextLine();
            
            if(!name.equalsIgnoreCase("si"))
                exit = true;
        }
    }
}
