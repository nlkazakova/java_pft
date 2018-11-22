package ru.stqa.pft.sandbox;

public class MyFirstProgram {
	
	public static void main (String[] args){
		System.out.println("Hello, world!");

		Point p1 = new Point(100, 100);
		Point p2 = new Point(200, 100);

		System.out.println("Расстояние между точками P1 (" + p1.x + "," + p1.y + ") и P2 (" + p2.x + "," + p2.y + ") равно: " + distance(p1, p2) + " (вычисление с помощью функции)");
		System.out.println("Расстояние между точками P1 (" + p1.x + "," + p1.y + ") и P2 (" + p2.x + "," + p2.y + ") равно: " + p1.distance(p2) + " (вычисление с помощью метода)");
	}

	public static double distance(Point p1, Point p2) {

		return Math.sqrt(Math.pow((p2.x - p1.x), 2) + Math.pow((p2.y - p1.y),2));
	}

}