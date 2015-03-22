package org.team2168.commands.arcb;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Retracts both of the ARCB arms.
 */
public class ARCBRetract extends CommandGroup {

	public ARCBRetract() {
		addSequential(new ARCBRetractLeft());
		addSequential(new ARCBRetractRight());
	}
}
