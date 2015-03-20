package org.team2168.commands.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.team2168.commands.arcb.ARCBRetractLeft;
import org.team2168.commands.arcb.ARCBRetractRight;

/**
 * Retracts both of the ARCB arms.
 */
public class ARCBRetract extends CommandGroup {

	public ARCBRetract() {
    addSequential(new ARCBRetractLeft());
    addSequential(new ARCBRetractRight());
	}
}
