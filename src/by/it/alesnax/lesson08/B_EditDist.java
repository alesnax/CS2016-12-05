package by.it.alesnax.lesson08;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

/*
Задача на программирование: расстояние Левенштейна
    https://ru.wikipedia.org/wiki/Расстояние_Левенштейна
    http://planetcalc.ru/1721/

Дано:
    Две данных непустые строки длины не более 100, содержащие строчные буквы латинского алфавита.

Необходимо:
    Решить задачу МЕТОДАМИ ДИНАМИЧЕСКОГО ПРОГРАММИРОВАНИЯ
    Итерационно вычислить расстояние редактирования двух данных непустых строк

    Sample Input 1:
    ab
    ab
    Sample Output 1:
    0

    Sample Input 2:
    short
    ports
    Sample Output 2:
    3

    Sample Input 3:
    distance
    editing
    Sample Output 3:
    5

*/

public class B_EditDist {


    int getDistanceEdinting(String one, String two) {

        String str1 = one;
        String str2 = two;
        int[][] editDist = new int[str1.length()+1][str2.length()+1];

        for (int i = 0; i <= str1.length(); i++) {
            editDist[i][0] = i;
        }
        for (int j = 0; j <= str2.length(); j++) {
            editDist[0][j] = j;
        }

        for (int i = 1; i <= str1.length(); i++) {
            for (int j = 1; j <= str2.length(); j++) {
                if (i == 0) {
                    editDist[i][j] = j;
                } else if (j == 0) {
                    editDist[i][j] = i;
                } else {
                    int discrep = (str1.charAt(i - 1) == str2.charAt(j - 1)) ? 0 : 1;
                    int ins = editDist[i - 1][j] + 1;
                    int del = editDist[i][j - 1] + 1;
                    int rep = editDist[i - 1][j - 1] + discrep;
                    editDist[i][j] = Math.min(Math.min(ins, del), rep);
                }
            }
        }

        return editDist[str1.length()][str2.length()];
    }



    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelov/lesson08/dataABC.txt");
        B_EditDist instance = new B_EditDist();
        Scanner scanner = new Scanner(stream);
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
    }

}
