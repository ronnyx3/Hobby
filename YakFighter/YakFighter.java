package com.rong.bots.YakFighter;

import com.runemate.game.api.script.framework.task.TaskBot;

public class YakFighter extends TaskBot {
    @Override
    public void onStart(String... strings)
    {
        setLoopDelay(100, 200);
        add(new AttackYak(), new CheckInCombat());
    }
}
