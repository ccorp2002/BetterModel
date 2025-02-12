package kr.toxicity.model.compatibility.mythicmobs

import io.lumine.mythic.api.config.MythicLineConfig
import io.lumine.mythic.api.skills.INoTargetSkill
import io.lumine.mythic.api.skills.SkillMetadata
import io.lumine.mythic.api.skills.SkillResult
import io.lumine.mythic.bukkit.MythicBukkit
import io.lumine.mythic.core.skills.SkillMechanic
import kr.toxicity.model.api.util.EntityUtil
import kr.toxicity.model.manager.ModelManagerImpl

class ModelMechanic(mlc: MythicLineConfig) : SkillMechanic(MythicBukkit.inst().skillManager, null, "[BetterModel]", mlc), INoTargetSkill {

    private val mid = mlc.getString(arrayOf("mid", "m", "model"))!!

    init {
        isAsyncSafe = false
    }

    override fun cast(p0: SkillMetadata): SkillResult {
        return ModelManagerImpl.renderer(mid)?.let {
            val e = p0.caster.entity.bukkitEntity
            it.create(e).run {
                val loc = e.location
                loc.world.getNearbyPlayers(loc, EntityUtil.RENDER_DISTANCE, EntityUtil.RENDER_DISTANCE, EntityUtil.RENDER_DISTANCE).forEach { p ->
                    spawn(p)
                }
            }
            SkillResult.SUCCESS
        } ?: SkillResult.ERROR
    }
}