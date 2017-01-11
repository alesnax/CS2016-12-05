package by.it.alesnax.lesson05;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

/*
Видеорегистраторы и площадь.
На площади установлена одна или несколько камер.
Известны данные о том, когда каждая из них включалась и выключалась (отрезки работы)
Известен список событий на площади (время начала каждого события).
Вам необходимо определить для каждого события сколько камер его записали.

В первой строке задано два целых числа:
    число включений камер (отрезки) 1<=n<=50000
    число событий (точки) 1<=m<=50000.

Следующие n строк содержат по два целых числа ai и bi (ai<=bi) -
координаты концов отрезков (время работы одной какой-то камеры).
Последняя строка содержит m целых чисел - координаты точек.
Все координаты не превышают 10E8 по модулю (!).

Точка считается принадлежащей отрезку, если она находится внутри него или на границе.

Для каждой точки в порядке их появления во вводе выведите,
скольким отрезкам она принадлежит.
    Sample Input:
    2 3
    0 5
    7 10
    1 6 11
    Sample Output:
    1 0 0

*/

public class A_QSort {

    //отрезок
    private class Dot implements Comparable <Dot>{
        int x;
        int index;

        public Dot(int x, int index){
            this.x = x;
            this.index = index;
        }

        @Override
        public int compareTo(Dot o) {
            if (x-o.x != 0){
                return x-o.x;
            }else{
                return index - o.index;
            }
        }
    }


    int[] getAccessory(InputStream stream) throws FileNotFoundException {
        //подготовка к чтению данных
        Scanner scanner = new Scanner(stream);
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        //число отрезков отсортированного массива
        int periods = scanner.nextInt();

        //число точек
        int dotsNum = scanner.nextInt();

        Dot[] dotsArr = new Dot[2* periods + dotsNum];
        int[] result=new int[dotsNum];
        int d = 0;
        for (int i = 0; i < periods; i++) {
            int begin = scanner.nextInt();
            int end = scanner.nextInt();
            if (begin > end){
                int temp = begin;
                begin = end;
                end = temp;
            }
            dotsArr[d++]= new Dot(begin, -1);
            dotsArr[d++]= new Dot(end, dotsArr.length);
        }
        for (int i = 0; i < dotsNum; i++) {
            int x = scanner.nextInt();
            dotsArr[d++] = new Dot(x, i);
        }
        //тут реализуйте логику задачи с применением быстрой сортировки
        //в классе отрезка Segment реализуйте нужный для этой задачи компаратор

        qSort(dotsArr, 0, dotsArr.length);

        int workingCam = 0;
        for (int i = 0; i < dotsArr.length; i++) {
                if (dotsArr[i].index<0){
                    workingCam++; // камера включена
                } else if (dotsArr[i].index == dotsArr.length){
                    workingCam--; // камера выключена
                } else{
                    result[dotsArr[i].index] = workingCam;
                }
        }
        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result;
    }

    private int partition (Dot arr[], int low, int high){
        int mid = low;
        Dot x = arr[low];
        for (int i = low+1; i < high; i++) {
            if (arr[i].compareTo(x)<= 0){
                mid++;
                Dot t= arr[i];
                arr[i] = arr[mid];
                arr[mid] = t;
            }
            Dot t = arr[low];
            arr[low] = arr[mid];
            arr[mid] = t;
        }
        return mid;
    }
    private void qSort(Dot arr[], int low, int high){
        if (low < high){
            int mid = partition(arr, low, high);
            qSort(arr, low, mid -1);
            qSort(arr, mid +1, high);
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/alesnax/lesson05/dataA.txt");
        A_QSort instance = new A_QSort();
        int[] result=instance.getAccessory(stream);
        for (int index:result){
            System.out.print(index+" ");
        }
    }

}
