package jumpingalien.part1.facade;

import jumpingalien.model.Mazub;

public class Facade implements IFacade {
	public double[] getAcceleration(Mazub alien){
		return new double[] {alien.getAcc(), alien.getYAcc()};
	}
}
