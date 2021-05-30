package com.lottery;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CheckBall {
	// enum RedBall String[];

	GetBallValues gbv = new GetBallValues();

	public CheckBall() {

	}

	public CheckBall(String _red01) {

		gbv.getAllBall();
		gbv.checkBall(_red01);
	}

	public CheckBall(String _red01, String _red02) {
		String[] redballs = { _red01, _red02 };
		gbv.getAllBall();
		gbv.checkBall(redballs);
	}

	public CheckBall(String[] redballs) {
		gbv.getAllBall();
		gbv.checkBall(redballs);
	}

	public void CheckOneBall(String[] redballs) {
		gbv.getAllBall();
		try {
			int size = redballs.length;
			for (int i = 0; i < size; i++) {
				ArrayList sub = new ArrayList(Arrays.asList(redballs));
				sub.remove(i);
				gbv.checkBall(sub, true);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void CheckTwoBall(String[] redballs) {
		if (redballs.length > 2) {
			gbv.getAllBall();
			try {
				int size = redballs.length;
				for (int i = 0; i < size - 1; i++) {
					for (int j = redballs.length - 1; j > i; j--) {
						ArrayList sub = new ArrayList(Arrays.asList(redballs));
						sub.remove(i);
						sub.remove(j - 1);

						// System.out.println(sub.toString());
						gbv.checkBall(sub, false);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else
			System.out.println("Error");
	}

	public CheckBall(String _red01, String _red02, String _red03) {
		String[] redballs = { _red01, _red02, _red03 };
		//if(gbv.checknumber())
		gbv.getAllBall();
		gbv.checkBall(redballs);
	}

	public CheckBall(String _red01, String _red02, String _red03, String _red04) {
		String[] redballs = { _red01, _red02, _red03, _red04 };
		gbv.getAllBall();
		gbv.checkBall(redballs);
	}

	public CheckBall(String _red01, String _red02, String _red03,
			String _red04, String _red05) {
		String[] redballs = { _red01, _red02, _red03, _red04, _red05 };
		gbv.getAllBall();
		gbv.checkBall(redballs);
	}

	public CheckBall(String _red01, String _red02, String _red03,
			String _red04, String _red05, String _red06) {
		String[] redballs = { _red01, _red02, _red03, _red04, _red05, _red06 };
		gbv.getAllBall();
		gbv.checkBall(redballs);
	}

	public void showNewOne() {
		// List li = Arrays.asList(gbv.getRecentOne());
		// System.out.println(li);
//		String[] aa = gbv.getRecentOne();
//		if (aa != null) {
//			List li = Arrays.asList(aa);
//		} else
//			System.out.println("Don't find any data in table!");
	}
}
