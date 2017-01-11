package by.it.alesnax.lesson05;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Scanner;

/*
Первая строка содержит число 1<=n<=10000, вторая - n натуральных чисел, не превышающих 10.
Выведите упорядоченную по неубыванию последовательность этих чисел.

При сортировке реализуйте метод со сложностью O(n)

Пример: https://karussell.wordpress.com/2010/03/01/fast-integer-sorting-algorithm-on/
Вольный перевод: http://programador.ru/sorting-positive-int-linear-time/
*/

public class B_CountSort {


    int[] countSort(InputStream stream) throws FileNotFoundException {
        //подготовка к чтению данных
        Scanner scanner = new Scanner(stream);
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        //размер массива
        int n = scanner.nextInt();
        int[] points = new int[n];
        int low = points[0];
        int high = 0;
        //читаем точки
        for (int i = 0; i < n; i++) {
            points[i]=scanner.nextInt();
            if (points[i] > high){
                high = points[i];
            }else if (points[i] < low){
                low = points[i];
            }
        }
        //тут реализуйте логику задачи с применением сортировки подсчетом

        countSort(points, low, high);
        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return points;
    }

    private int[] countSort(int[] dots, int low, int high){

       int[] countsArr = new int[high - low + 1];

       for (int i = 0; i < dots.length; i++) countsArr[dots[i] - low]++;

       int index = 0;
       for (int i = 0; i < countsArr.length; i++) {
           if (countsArr[i]>0){
               Arrays.fill(dots, index, index + countsArr[i], i + low);
               index += countsArr[i];
           }
       }
       return dots;
    }

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/alesnax/lesson05/dataB.txt");
        B_CountSort instance = new B_CountSort();
        int[] result=instance.countSort(stream);
        for (int index:result){
            System.out.print(index+" ");
        }
    }
}