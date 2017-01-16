package by.it.alesnax.lesson08;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Scanner;

/*
Задача на программирование: расстояние Левенштейна
    https://ru.wikipedia.org/wiki/Расстояние_Левенштейна
    http://planetcalc.ru/1721/

Дано:
    Две данных непустые строки длины не более 100, содержащие строчные буквы латинского алфавита.

Необходимо:
    Решить задачу МЕТОДАМИ ДИНАМИЧЕСКОГО ПРОГРАММИРОВАНИЯ
    Рекурсивно вычислить расстояние редактирования двух данных непустых строк

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

public class A_EditDist {


    static int [][] editDist;
    String str1;
    String str2;
    int getDistanceEdinting(String one, String two) {
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        str1 = one;
        str2 = two;

        editDist = new int [str1.length()+1][str2.length()+1];
        for(int [] row : editDist) Arrays.fill(row ,Integer.MAX_VALUE);

        int n = str1.length();
        int m = str2.length();
        return Edit(n, m);
    }

    private int Edit(int n, int m)
    {
        if(editDist[n][m]==Integer.MAX_VALUE)
        {
            if(n == 0){
                editDist[n][m] = m;
            }
            else if(m == 0) {
                editDist[n][m] = n;
            }else{
                int discrep = (str1.charAt(n - 1) == str2.charAt(m - 1)) ? 0 : 1;
                int ins = Edit(n, m - 1)+1;
                int del = Edit(n - 1, m)+1;
                int rep = Edit(n - 1, m - 1) + discrep;
                editDist[n][m] = Math.min(Math.min(ins, del), rep);
            }
        }
        return editDist[n][m];
    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelov/lesson08/dataABC.txt");
        A_EditDist instance = new A_EditDist();
        Scanner scanner = new Scanner(stream);
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
    }
}

