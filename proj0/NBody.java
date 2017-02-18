public class NBody {
	public static double readRadius(String path) {
		In in = new In(path);
		int n = in.readInt();
		double radius = in.readDouble();
		return radius;
	}

	public static Planet[] readPlanets(String path) {
		In in = new In(path);
		int n = in.readInt();
		int i = 0;
		Planet[] planets;
		double radius = in.readDouble();
		planets = new Planet[n];
		while (i < n) {
			double xxPos = in.readDouble();
			double yyPos = in.readDouble();
			double xxVel = in.readDouble();
			double yyVel = in.readDouble();
			double mass = in.readDouble();
			String img = in.readString();
			planets[i] = new Planet (xxPos, yyPos, xxVel, yyVel, mass, img);
			i = i + 1;
		}
		return planets;
	}

	public static void main (String[] args) {
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];
		double radius = readRadius(filename);
		Planet[] planets = readPlanets(filename);
		String background = "images/starfield.jpg";
		StdDraw.setScale(-radius,radius);
		StdDraw.clear();
		StdDraw.picture(0,0,background,2*radius,2*radius);
		int n = planets.length;
		int i = 0;
		while (i < n) {
			planets[i].draw();
			i += 1;
		}
		StdDraw.show(10);
		double time = 0;
		double[] xForces;
		double[] yForces;
		xForces = new double[n];
		yForces = new double[n];
		while (time < T) {
			i = 0;
			while (i < n) {
				xForces[i] = planets[i].calcNetForceExertedByX(planets);
				yForces[i] = planets[i].calcNetForceExertedByY(planets);
				i += 1;
			}
			i = 0;
			while (i < n) {
				planets[i].update(dt, xForces[i], yForces[i]);
				i += 1;
			}
			StdDraw.setScale(-radius,radius);
			StdDraw.clear();
			StdDraw.picture(0,0,background,2*radius,2*radius);
			i = 0;
			while (i < n) {
				planets[i].draw();
				i += 1;
			}
			StdDraw.show(10);
			time += dt;
		}
		StdOut.printf("%d\n", planets.length);
		StdOut.printf("%.2e\n", radius);
		for (i = 0; i < planets.length; i++) {
			StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
   			planets[i].xxPos, planets[i].yyPos, planets[i].xxVel, planets[i].yyVel, planets[i].mass, planets[i].imgFileName);	
		}	
	}
}
