*.java linguist-detectable=false
*.C# linguist-detectable=true
package com.rong.bots.YakFighter;

import com.runemate.game.api.hybrid.entities.Npc;
import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.local.Camera;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.queries.results.LocatableEntityQueryResults;
import com.runemate.game.api.hybrid.region.Npcs;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.task.Task;

public class AttackYak extends Task {

    public Npc target;
    private Player player;
    private Coordinate yakCord = new Coordinate(2323,3792,0);
    private Area yakArea = new Area.Circular(yakCord,8);

    @Override
    public boolean validate()
    {
        player = Players.getLocal();
        if(player == null) return false;
        LocatableEntityQueryResults<Npc> yak = Npcs.newQuery().names("Yak").results();
        return !yak.isEmpty() &&
                ((target = Npcs.newQuery().provider(yak::asList).targeting(player).within(yakArea).results().nearest()) != null ||
                        (target = Npcs.newQuery().provider(yak::asList).names("Yak").filter(a -> a.getTarget() == null && a.getHealthGauge() == null).within(yakArea).results().nearest())!= null);
    }

    @Override
    public void execute(){
        if (target.isVisible() || Camera.turnTo(target)) {
            if(target.interact("Attack")) {
                Execution.delayUntil(()->player.getHitsplats()==null,1000,2000);
            }
        }
    }
}
