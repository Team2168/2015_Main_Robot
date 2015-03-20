package org.team2168.commands.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.team2168.commands.arcb.ARCBDeployLeft;
import org.team2168.commands.arcb.ARCBDeployRight;

/**
 * Extends both of the ARCB arms.
 */
public class ARCBDeploy extends CommandGroup {

	public ARCBDeploy() {
    addSequential(new ARCBDeployLeft());
    addSequential(new ARCBDeployRight());
	}
}
