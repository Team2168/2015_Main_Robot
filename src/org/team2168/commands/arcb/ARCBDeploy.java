package org.team2168.commands.arcb;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Extends both of the ARCB arms.
 */
public class ARCBDeploy extends CommandGroup {

	public ARCBDeploy() {
		addSequential(new ARCBDeployLeft());
		addSequential(new ARCBDeployRight());
	}
}
