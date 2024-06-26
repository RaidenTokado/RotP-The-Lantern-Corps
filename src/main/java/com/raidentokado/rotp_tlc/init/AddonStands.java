package com.raidentokado.rotp_tlc.init;

import com.github.standobyte.jojo.entity.stand.StandEntityType;
import com.github.standobyte.jojo.init.power.stand.EntityStandRegistryObject.EntityStandSupplier;
import com.github.standobyte.jojo.power.impl.stand.stats.StandStats;
import com.github.standobyte.jojo.power.impl.stand.type.EntityStandType;
import com.raidentokado.rotp_tlc.entity.stand.stands.GreenLanternEntity;

public class AddonStands {

    public static final EntityStandSupplier<EntityStandType<StandStats>, StandEntityType<GreenLanternEntity>>
            GREEN_LANTERN = new EntityStandSupplier<>(InitStands.STAND_GREEN_LANTERN);
}