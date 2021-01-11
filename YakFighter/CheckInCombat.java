package com.rong.bots.YakFighter;

import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.task.Task;

public class CheckInCombat extends Task {

    public boolean validate() {
        Player me = Players.getLocal();
        return me != null && me.getTarget() != null;
    }

    @Override
    public void execute() {
        Execution.delay(300, 400);
    }
}
