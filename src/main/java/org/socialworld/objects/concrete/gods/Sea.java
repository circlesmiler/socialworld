package org.socialworld.objects.concrete.gods;

import org.socialworld.objects.God;
import org.socialworld.objects.State;

public class Sea extends God {



	@Override
	public boolean checkObjectBelongsToGroup(short groupNumberSuffix) {
		// TODO Auto-generated method stub
		return false;
	}

	protected int getGNS()  // GroupingNumberSuffix
	{
		return 1;
	}

	@Override
	protected State getInitState(String stateClassName) {
		// TODO Auto-generated method stub
		return State.getObjectNothing();
	}

}
