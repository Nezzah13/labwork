/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

public class JavaApplication1 {
    
    // задача окон в формате х1 х2 у1 у2
    static double window1[] = {0, 1000, 0, 1000};
    static double window2[] = {100, 900, 700, 950};
    static double window3[] = {120, 620, 20, 920};
    static double window4[] = {350, 550, 350, 550};
    static double window5[] = {400, 510, 470, 530};
    static double windowarray[][] = {window1, window2, window3, window4, window5};
    static int windowamount = 5;
    
    public static void help() {
        System.out.println("Создатель: Захаров Николай Сергеевич 09-952(1) \nВозможные ключи: -h -help -? -x -y\nИспользование: app -x <координата Х> -y <Координата Y>");
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        double x = -1;
        double y = -1;
        if (args.length == 0) {
            help();
            return;
        } else {
            for (int i = 0; i < args.length; i++) {
                if ((args[i].equals("-h")) || (args[i].equals("-help")) || (args[i].equals("-?"))) {
                    help();
                    return;
                } else
                if (args[0].toLowerCase().equals("-x")) {
                    if (args[i].toLowerCase().equals("-x")) {
                        try {
                            x = Double.parseDouble(args[i+1]);
                        } catch (NumberFormatException e) {
                            if (x == -1)
                                System.out.println("Не указан аргумент Х");
                            else
                                System.out.println("Неверный аргумент Х " + args[i+1] + "\nДесятичные дроби необходимо вводить через точку");
                            help();
                            return;
                        }
                    }
                    if (args[i].toLowerCase().equals("-y")) {
                        try {
                            y = Double.parseDouble(args[i+1]);
                        } catch (NumberFormatException e) {
                            if (y == -1)
                                System.out.println("Не указан аргумент Y");
                            else
                                System.out.println("Неверный аргумент Y: " + args[i+1] + "\nДесятичные дроби необходимо вводить через точку");
                            help();
                            return;
                        }
                    }
                } else {
                    System.out.println("Ошибка: неожиданный аргумент");
                    help();
                    return;
                }
            }           
            
            if ((x > 0) && (y > 0)) {
                if (determineSelectedWindow(x, y) == -1) {
                    System.out.println("Удовлетворяющего введенным координатам окна не существует");
                    help();
                    return;
                } else {
                    System.out.println("Искомое окно - окно номер " + determineSelectedWindow(x, y));
                }
            } else {
                if (x < 0) {
                    System.out.println("Ошибка: Неверно указана координата Х" + args[1] + "\nКоордината либо не указана, либо отрицательна");
                }
                if (y < 0) {
                    System.out.println("Ошибка: Неверно указана координата Y" + args[3] + "\nКоордината либо не указана, либо отрицательна");
                }
                help();
                return;
            }
            
        }   
    }
    
    public static int determineSelectedWindow(Double x, Double y) {
        int output = -1;
        for (int i = --windowamount; i > -1; i--) {
            if (x >= windowarray[i][0] && (x <= windowarray[i][1]) && (y >= windowarray[i][2]) && (y <= windowarray[i][3])) {
                output = ++i;
                break;
            }
        }
        return output;
    }
    
}
