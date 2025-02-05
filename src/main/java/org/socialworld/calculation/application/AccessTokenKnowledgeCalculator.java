package org.socialworld.calculation.application;

import org.socialworld.calculation.SimulationCluster;
import org.socialworld.core.IAccessToken;

public final class AccessTokenKnowledgeCalculator implements IAccessToken
{

	private static AccessTokenKnowledgeCalculator valid;
	
	static AccessTokenKnowledgeCalculator getValid() {
		if (valid == null) {
			valid = new AccessTokenKnowledgeCalculator();
			SimulationCluster.knowledge.addToken(valid);
		}
		return valid;
	}
	
	public boolean isValid() {
		return this == valid;
	}
	
	public SimulationCluster getSimulationCluster() {
		return SimulationCluster.knowledge;
	}
	
}


