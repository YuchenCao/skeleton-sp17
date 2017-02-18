public class Planet {
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;

	public static double g = 6.67e-11;

	public Planet (double xP, double yP, double xV, double yV, double m, String img) {
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}

	public Planet (Planet p) {
		xxPos = p.xxPos;
		yyPos = p.yyPos;
		xxVel = p.xxVel;
		yyVel = p.yyVel;
		mass = p.mass;
		imgFileName = p.imgFileName;
	}

	public double calcDistance (Planet p) {
		double r2 = (p.xxPos - xxPos) * (p.xxPos - xxPos) + (p.yyPos - yyPos) * (p.yyPos - yyPos);
		return Math.sqrt(r2);
	} 

	public double calcForceExertedBy (Planet p) {
		double r = calcDistance(p);
		return g * mass * p.mass / (r * r);
	}

	public double calcForceExertedByX (Planet p) {
		double r = calcDistance(p);
		double f = calcForceExertedBy(p);
		return f * (p.xxPos - xxPos) / r;
	}

	public double calcForceExertedByY (Planet p) {
		double r = calcDistance(p);
		double f = calcForceExertedBy(p);
		return f * (p.yyPos - yyPos) / r;
	}

	public double calcNetForceExertedByX (Planet[] all) {
		int n = all.length;
		int i = 0;
		double f = 0;
		while (i < n) {
			if (all[i].equals(this)) {
			i = i + 1;
			continue;
			}
			f = f + calcForceExertedByX(all[i]);
			i = i + 1;
		}
		return f;
	}

	public double calcNetForceExertedByY (Planet[] all) {
		int n = all.length;
		int i = 0;
		double f = 0;
		while (i < n) {
			if (all[i].equals(this)) {
			i = i + 1;
			continue;
			}
			f = f + calcForceExertedByY(all[i]);
			i = i + 1;
		}
		return f;
	}

	public void update (double dt, double fx, double fy) {
		double ax = fx / mass;
		double ay = fy / mass;
		xxVel += dt * ax;
		yyVel += dt * ay;
		xxPos += dt * xxVel;
		yyPos += dt * yyVel;
	}

	public void draw () {
		StdDraw.picture(xxPos, yyPos, "./images/" + imgFileName);
	}
}
