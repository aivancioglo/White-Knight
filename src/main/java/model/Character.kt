package model

import model.equipment.Equipment
import model.equipment.Equipment.Companion.NONE
import model.equipment.armor.Armor
import model.equipment.shield.Shield
import model.equipment.weapon.Weapon
import model.support.Constants.ACTUAL
import model.support.Constants.DEBUFF
import model.support.Constants.EIGHTY
import model.support.Constants.FIFTEEN
import model.support.Constants.FIFTY
import model.support.Constants.FIVE
import model.support.Constants.FOUR
import model.support.Constants.HUNDRED
import model.support.Constants.INJURIES
import model.support.Constants.MAX
import model.support.Constants.ONE
import model.support.Constants.PERCENTAGE
import model.support.Constants.TEN
import model.support.Constants.THIRTY
import model.support.Constants.THREE
import model.support.Constants.TIREDNESS
import model.support.Constants.TWENTY
import model.support.Constants.TWO
import model.support.Constants.defaultEquipment
import model.support.Constants.defaultSkills
import model.support.Constants.defaultStats
import model.support.enums.*
import model.support.enums.ArmorType.*
import model.support.enums.AttackStyle.*
import model.support.enums.AttackType.*
import model.support.enums.BodyPart.*
import model.support.enums.DefenseStyle.*
import model.support.enums.DefenseType.*
import model.support.enums.HitPower.LOW
import model.support.enums.HitPower.MEDIUM
import model.support.enums.Skill.*
import model.support.enums.Specification.*
import java.lang.System.err

class Character(
        concentration: Double = 1.0,
        strength: Double = 1.0,
        agility: Double = 1.0,
        suppleness: Double = 1.0,
        quickness: Double = 1.0,
        val who: String,
        val whom: String = who,
        val toWhom: String = who,
        val byWhom: String = who,
        val aboutWhom: String = who,
        isMan: Boolean = true,
        isRightHanded: Boolean = Math.random() < 0.98,
        private var ai: Boolean = true,
        attackStyle: AttackStyle = AGGRESSIVE_OFFENSIVE,
        defenseStyle: DefenseStyle = QUICK_DEFENSE
) {
    var isMan = isMan
        private set

    var isRightHanded = isRightHanded
        private set

    var isAlive = true
        private set

    private var attackedLastStep = false
    private var defendedLastStep = false

    var attackStyle = attackStyle
        private set

    var defenseStyle = defenseStyle
        private set

    var wantToAttack = true
        private set

    var onTheKnees = false
        private set

    private val concentration = hashMapOf(ACTUAL to 0.0, MAX to concentration, DEBUFF to 0.0)
    private val strength = hashMapOf(ACTUAL to 0.0, MAX to strength, DEBUFF to 0.0)
    private val agility = hashMapOf(ACTUAL to 0.0, MAX to agility, DEBUFF to 0.0)
    private val suppleness = hashMapOf(ACTUAL to 0.0, MAX to suppleness, DEBUFF to 0.0)
    private val quickness = hashMapOf(ACTUAL to 0.0, MAX to quickness, DEBUFF to 0.0)

    private val health = hashMapOf(ACTUAL to 0.0, MAX to 0.0, INJURIES to 0.0, DEBUFF to 0.0, PERCENTAGE to 0.0)
    private val stamina = hashMapOf(ACTUAL to 0.0, MAX to 0.0, TIREDNESS to 0.0, DEBUFF to 0.0, PERCENTAGE to 0.0)

    private val skills = defaultSkills()

    // Equipment

    private val equipments = defaultEquipment()
    private val defense = defaultStats()

    var equipmentWeight = 0.0
        private set

    var equipmentPrice = 0.0
        private set

    // Hand-to-hand stats

    private val handToHandDamage = defaultStats()
    private val handToHandCriticalDamage = defaultStats()

    var neededStaminaForHandToHandAttack = 0.0
        private set

    private val handToHandActionFrequency = hashMapOf(ACTUAL to 0.0, MAX to 0.0, DEBUFF to 0.0)
    private val handToHandAttackDuration = hashMapOf(ACTUAL to 0.0, MAX to 0.0, DEBUFF to 0.0)
    private val handToHandDefenseDuration = hashMapOf(ACTUAL to 0.0, MAX to 0.0, DEBUFF to 0.0)

    // Primary weapon stats

    private val primaryWeaponDamage = defaultStats()
    private val primaryWeaponCriticalDamage = defaultStats()

    var neededStaminaForPrimaryWeaponAttack = 0.0
        private set

    private val primaryWeaponUsingFrequency = hashMapOf(ACTUAL to 0.0, MAX to 0.0, DEBUFF to 0.0)
    private val primaryWeaponAttackDuration = hashMapOf(ACTUAL to 0.0, MAX to 0.0, DEBUFF to 0.0)
    private val primaryWeaponDefenseDuration = hashMapOf(ACTUAL to 0.0, MAX to 0.0, DEBUFF to 0.0)

    // Secondary weapon stats

    private val secondaryWeaponDamage = defaultStats()
    private val secondaryWeaponCriticalDamage = defaultStats()

    var neededStaminaForSecondaryWeaponAttack = 0.0
        private set

    private val secondaryWeaponUsingFrequency = hashMapOf(ACTUAL to 0.0, MAX to 0.0, DEBUFF to 0.0)
    private val secondaryWeaponAttackDuration = hashMapOf(ACTUAL to 0.0, MAX to 0.0, DEBUFF to 0.0)
    private val secondaryWeaponDefenseDuration = hashMapOf(ACTUAL to 0.0, MAX to 0.0, DEBUFF to 0.0)

    // Shield stats

    var neededStaminaForShieldUsing = 0.0
        private set

    private val shieldUsingFrequency = hashMapOf(ACTUAL to 0.0, MAX to 0.0, DEBUFF to 0.0)
    private val shieldUsingDuration = hashMapOf(ACTUAL to 0.0, MAX to 0.0, DEBUFF to 0.0)

    // Other stats

    var neededStaminaToDodge = 0.0
        private set

    private val dodgeFrequency = hashMapOf(ACTUAL to 0.0, MAX to 0.0, DEBUFF to 0.0)
    private val dodgeDuration = hashMapOf(ACTUAL to 0.0, MAX to 0.0, DEBUFF to 0.0)

    var attackAction = 1.0
        private set

    var defenseAction = 1.0
        private set

    private var battleCombination = ArrayList<Technique>()

    private val inventory = ArrayList<Equipment>()

    init {
        updateStats()
        newStep()
    }

    fun increaseConcentration(increase: Double) {
        concentration[MAX] = concentration(MAX) + increase
        updateStats()
    }

    fun increaseStrength(increase: Double) {
        strength[MAX] = strength(MAX) + increase
        updateStats()
    }

    fun increaseAgility(increase: Double) {
        agility[MAX] = agility(MAX) + increase
        updateStats()
    }

    fun increaseSuppleness(increase: Double) {
        suppleness[MAX] = suppleness(MAX) + increase
        updateStats()
    }

    fun increaseQuickness(increase: Double) {
        quickness[MAX] = quickness(MAX) + increase
        updateStats()
    }

    fun increaseSkill(skill: Skill, increase: Double) {
        skills[skill] = skill(skill) + increase
        updateStats()
    }

    fun increaseInjuries(injury: Double) {
        health[INJURIES] = health(INJURIES) + injury
        updateStats()
    }

    fun increaseTiredness(tiredness: Double) {
        stamina[TIREDNESS] = stamina(TIREDNESS) + tiredness * 0.2
        stamina[DEBUFF] = stamina(DEBUFF) + tiredness * 0.8

        if (stamina(TIREDNESS) > stamina(MAX))
            stamina[TIREDNESS] = stamina(MAX)

        updateStats()
    }

    fun equipRightHand(equipment: Equipment) {
        when (equipment) {
            is Weapon -> {
                if ((strength() - equipment.weight - equipmentWeight - equipments[RIGHT_HAND]!!.weight) / FIVE + TWO <= 0) {
                    err.println("You can't equip ${equipment.weaponType}. It's too weighty!")
                    return
                }

                if (!equipment.isOneHanded) {
                    equipments[RIGHT_HAND] = equipment
                    equipments[LEFT_HAND] = NONE
                } else {
                    if (equipments[RIGHT_HAND] is Weapon && !(equipments[RIGHT_HAND] as Weapon).isOneHanded) {
                        equipments[RIGHT_HAND] = equipment
                        equipments[LEFT_HAND] = NONE
                    } else {
                        equipments[RIGHT_HAND] = equipment
                    }
                }

                updateStats()
            }

            is Shield -> {
                if ((strength() - equipment.weight - equipmentWeight - equipments[RIGHT_HAND]!!.weight) / FIVE + TWO <= 0) {
                    err.println("You can't equip ${equipment.shieldType}. It's too weighty!")
                    return
                }

                if (equipments[LEFT_HAND] is Weapon && !(equipments[LEFT_HAND] as Weapon).isOneHanded) {
                    equipments[RIGHT_HAND] = equipment
                    equipments[LEFT_HAND] = NONE
                } else {
                    if (equipments[LEFT_HAND] is Shield) {
                        equipments[RIGHT_HAND] = equipment
                        equipments[LEFT_HAND] = NONE
                    } else {
                        equipments[RIGHT_HAND] = equipment
                    }
                }

                updateStats()
            }

            else -> err.println("Right hand equipment should be weapon or shield.")
        }
    }

    fun equipLeftHand(equipment: Equipment) {
        when (equipment) {
            is Weapon -> {
                if ((strength() - equipment.weight - equipmentWeight - equipments[LEFT_HAND]!!.weight) / FIVE + TWO <= 0) {
                    err.println("You can't equip ${equipment.weaponType}. It's too weighty!")
                    return
                }

                if (!equipment.isOneHanded) {
                    equipments[LEFT_HAND] = equipment
                    equipments[RIGHT_HAND] = NONE
                } else {
                    if (equipments[LEFT_HAND] is Weapon && !(equipments[LEFT_HAND] as Weapon).isOneHanded) {
                        equipments[LEFT_HAND] = equipment
                        equipments[RIGHT_HAND] = NONE
                    } else {
                        equipments[LEFT_HAND] = equipment
                    }
                }

                updateStats()
            }

            is Shield -> {
                if ((strength() - equipment.weight - equipmentWeight - equipments[LEFT_HAND]!!.weight) / FIVE + TWO <= 0) {
                    err.println("You can't equip ${equipment.shieldType}. It's too weighty!")
                    return
                }

                if (equipments[RIGHT_HAND] is Weapon && !(equipments[RIGHT_HAND] as Weapon).isOneHanded) {
                    equipments[LEFT_HAND] = equipment
                    equipments[RIGHT_HAND] = NONE
                } else {
                    if (equipments[RIGHT_HAND] is Shield) {
                        equipments[LEFT_HAND] = equipment
                        equipments[RIGHT_HAND] = NONE
                    } else {
                        equipments[LEFT_HAND] = equipment
                    }
                }

                updateStats()
            }

            else -> err.println("Left hand equipment should be weapon or shield.")
        }
    }

    fun equip(armor: Armor) {
        if ((strength() - armor.weight - equipmentWeight + equipments[armor.bodyPart]!!.weight) / FIVE + TWO <= 0) {
            err.println("You can't equip ${armor.armorType}. It's too weighty!")
        } else if (primaryWeaponExist() && (strength() - armor.weight - equipmentWeight + equipments[armor.bodyPart]!!.weight) / FIVE + TWO <= 0) {
            err.println("You can't equip ${armor.armorType}. It's too weighty!")
        } else if (secondaryWeaponExist() && (strength() - armor.weight - equipmentWeight + equipments[armor.bodyPart]!!.weight) / FIVE + TWO <= 0) {
            err.println("You can't equip ${armor.armorType}. It's too weighty!")
        } else if (shieldExist() && (strength() - armor.weight - equipmentWeight + equipments[armor.bodyPart]!!.weight) / FIVE + TWO <= 0) {
            err.println("You can't equip ${armor.armorType}. It's too weighty!")
        } else {
            equipments[armor.bodyPart] = armor
            updateStats()
        }
    }

    fun unequip(part: BodyPart) {
        when (part) {
            HEAD -> equipments[HEAD] = NONE
            RIGHT_SHOULDER -> equipments[RIGHT_SHOULDER] = NONE
            LEFT_SHOULDER -> equipments[LEFT_SHOULDER] = NONE

            RIGHT_HAND -> if (equipments[RIGHT_HAND] is Weapon && !(equipments[RIGHT_HAND] as Weapon).isOneHanded) {
                equipments[RIGHT_HAND] = NONE
                equipments[LEFT_HAND] = NONE
            } else {
                equipments[RIGHT_HAND] = NONE
            }

            LEFT_HAND -> if (equipments[LEFT_HAND] is Weapon && !(equipments[LEFT_HAND] as Weapon).isOneHanded) {
                equipments[LEFT_HAND] = NONE
                equipments[RIGHT_HAND] = NONE
            } else {
                equipments[LEFT_HAND] = NONE
            }

            HANDS -> equipments[HANDS] = NONE
            BODY -> equipments[BODY] = NONE
            RIGHT_LEG -> equipments[RIGHT_LEG] = NONE
            LEFT_LEG -> equipments[LEFT_LEG] = NONE
            FOOTS -> equipments[FOOTS] = NONE
        }

        updateStats()
    }

    fun primaryWeaponExist() = equipments[RIGHT_HAND] is Weapon || equipments[LEFT_HAND] is Weapon
    fun secondaryWeaponExist() = equipments[RIGHT_HAND] is Weapon && equipments[LEFT_HAND] is Weapon
    fun shieldExist() = equipments[RIGHT_HAND] is Shield || equipments[LEFT_HAND] is Shield

    fun has(armor: ArmorType) = when (armor) {
        HELM -> equipments[HEAD] != NONE
        RIGHT_SHOULDER_ARMOR -> equipments[RIGHT_SHOULDER] != NONE
        LEFT_SHOULDER_ARMOR -> equipments[LEFT_SHOULDER] != NONE
        GLOVES -> equipments[HANDS] != NONE
        BODY_ARMOR -> equipments[BODY] != NONE
        RIGHT_LEG_ARMOR -> equipments[RIGHT_LEG] != NONE
        LEFT_LEG_ARMOR -> equipments[LEFT_LEG] != NONE
        BOOTS -> equipments[FOOTS] != NONE
    }

    fun concentration(type: String = ACTUAL) = concentration[type]!!
    fun strength(type: String = ACTUAL) = strength[type]!!
    fun agility(type: String = ACTUAL) = agility[type]!!
    fun suppleness(type: String = ACTUAL) = suppleness[type]!!
    fun quickness(type: String = ACTUAL) = quickness[type]!!

    fun health(type: String = ACTUAL) = health[type]!!
    fun stamina(type: String = ACTUAL) = stamina[type]!!

    fun skill(skill: Skill) = skills[skill]!!

    fun primaryWeapon(): Weapon {
        if (isRightHanded && equipments[RIGHT_HAND] is Weapon)
            return equipments[RIGHT_HAND] as Weapon

        if (equipments[LEFT_HAND] is Weapon)
            return equipments[LEFT_HAND] as Weapon

        throw Exception("There is no equipped weapon!")
    }

    fun secondaryWeapon(): Weapon {
        if (!isRightHanded && equipments[LEFT_HAND] is Weapon)
            return equipments[LEFT_HAND] as Weapon

        if (equipments[RIGHT_HAND] is Weapon)
            return equipments[RIGHT_HAND] as Weapon

        throw Exception("There is no secondary weapon!")
    }

    fun shield(): Shield {
        if (equipments[RIGHT_HAND] is Shield)
            return equipments[RIGHT_HAND] as Shield

        if (equipments[LEFT_HAND] is Shield)
            return equipments[LEFT_HAND] as Shield

        throw Exception("There is no equipped shield!")
    }

    fun equipmentOf(bodyPart: BodyPart) = equipments[bodyPart]!!
    fun totalDefense(specification: Specification) = defense[specification]!!
    fun handToHandDamage(specification: Specification) = handToHandDamage[specification]!!
    fun handToHandCriticalDamage(specification: Specification) = handToHandCriticalDamage[specification]!!
    fun handToHandActionFrequency(type: String = ACTUAL) = handToHandActionFrequency[type]!!
    fun handToHandAttackDuration(type: String = ACTUAL) = handToHandAttackDuration[type]!!
    fun handToHandDefenseDuration(type: String = ACTUAL) = handToHandDefenseDuration[type]!!
    fun primaryWeaponDamage(specification: Specification) = primaryWeaponDamage[specification]!!
    fun primaryWeaponCriticalDamage(specification: Specification) = primaryWeaponCriticalDamage[specification]!!
    fun primaryWeaponUsingFrequency(type: String = ACTUAL) = primaryWeaponUsingFrequency[type]!!
    fun primaryWeaponAttackDuration(type: String = ACTUAL) = primaryWeaponAttackDuration[type]!!
    fun primaryWeaponDefenseDuration(type: String = ACTUAL) = primaryWeaponDefenseDuration[type]!!
    fun secondaryWeaponDamage(specification: Specification) = secondaryWeaponDamage[specification]!!
    fun secondaryWeaponCriticalDamage(specification: Specification) = secondaryWeaponCriticalDamage[specification]!!
    fun secondaryWeaponUsingFrequency(type: String = ACTUAL) = secondaryWeaponUsingFrequency[type]!!
    fun secondaryWeaponAttackDuration(type: String = ACTUAL) = secondaryWeaponAttackDuration[type]!!
    fun secondaryWeaponDefenseDuration(type: String = ACTUAL) = secondaryWeaponDefenseDuration[type]!!
    fun shieldDefense(specification: Specification) = shield().defense[specification]!!
    fun shieldUsingFrequency(type: String = ACTUAL) = shieldUsingFrequency[type]!!
    fun shieldUsingDuration(type: String = ACTUAL) = shieldUsingDuration[type]!!
    fun dodgeFrequency(type: String = ACTUAL) = dodgeFrequency[type]!!
    fun dodgeDuration(type: String = ACTUAL) = dodgeDuration[type]!!

    fun minAttackDuration() = if (handToHandAttackDuration() * LOW.mod < primaryWeaponAttackDuration() * LOW.mod) {
        if (handToHandAttackDuration() * LOW.mod < secondaryWeaponAttackDuration() * LOW.mod)
            handToHandAttackDuration() * LOW.mod
        else
            secondaryWeaponAttackDuration() * LOW.mod
    } else if (primaryWeaponAttackDuration() * LOW.mod < secondaryWeaponAttackDuration() * LOW.mod)
        primaryWeaponAttackDuration() * LOW.mod
    else
        secondaryWeaponAttackDuration() * LOW.mod

    fun minDefenseDuration() = if (shieldExist() && shieldUsingDuration() < dodgeDuration())
        shieldUsingDuration()
    else
        dodgeDuration()

    fun maxPossibleDamageTo(enemy: Character): Double {
        var maxDamage = 0.0

        for (specification in Specification.values()) {
            if (maxDamage < handToHandCriticalDamage(specification) - enemy.totalDefense(specification))
                maxDamage = handToHandCriticalDamage(specification) - enemy.totalDefense(specification)

            if (primaryWeaponExist() && maxDamage < primaryWeaponCriticalDamage(specification) - enemy.totalDefense(specification))
                maxDamage = primaryWeaponCriticalDamage(specification) - enemy.totalDefense(specification)

            if (secondaryWeaponExist() && maxDamage < secondaryWeaponCriticalDamage(specification) - enemy.totalDefense(specification))
                maxDamage = secondaryWeaponCriticalDamage(specification) - enemy.totalDefense(specification)
        }

        return maxDamage
    }

    fun mostEffectiveSpecificationAgainst(enemy: Character, yourAttackType: AttackType): Specification {
        var bestSpecification = BLUNT
        var bestDamage = 0.0

        when (yourAttackType) {
            RIGHT_HAND_ATTACK -> for (spec in Specification.values()) {
                val damage = Technique(this, RIGHT_HAND_ATTACK, MEDIUM, spec).damage - enemy.totalDefense(spec)
                if (bestDamage < damage) {
                    bestDamage = damage
                    bestSpecification = spec
                }
            }
            LEFT_HAND_ATTACK -> for (spec in Specification.values()) {
                val damage = Technique(this, LEFT_HAND_ATTACK, MEDIUM, spec).damage - enemy.totalDefense(spec)
                if (bestDamage < damage) {
                    bestDamage = damage
                    bestSpecification = spec
                }
            }
            PRIMARY_WEAPON_ATTACK -> for (spec in Specification.values()) {
                val damage = Technique(this, PRIMARY_WEAPON_ATTACK, MEDIUM, spec).damage - enemy.totalDefense(spec)
                if (bestDamage < damage) {
                    bestDamage = damage
                    bestSpecification = spec
                }
            }
            SECONDARY_WEAPON_ATTACK -> for (spec in Specification.values()) {
                val damage = Technique(this, SECONDARY_WEAPON_ATTACK, MEDIUM, spec).damage - enemy.totalDefense(spec)
                if (bestDamage < damage) {
                    bestDamage = damage
                    bestSpecification = spec
                }
            }
        }

        return bestSpecification
    }

    fun addToNextCombo(type: AttackType, power: HitPower, specification: Specification): Boolean {
        val technique = Technique(this, type, power, specification)
        return if (technique.canBeUsed())
            battleCombination.add(technique)
        else
            false
    }

    fun hasNextTechnique() = battleCombination.size > 0

    fun nextTechniqueUsing(): Technique {
        attackedLastStep = true

        val technique = battleCombination[0]
        battleCombination.remove(technique)

        attackAction -= when (technique.type) {
            RIGHT_HAND_ATTACK, LEFT_HAND_ATTACK -> handToHandActionFrequency(ACTUAL)
            PRIMARY_WEAPON_ATTACK -> primaryWeaponUsingFrequency(ACTUAL)
            SECONDARY_WEAPON_ATTACK -> secondaryWeaponUsingFrequency(ACTUAL)
        }

        increaseTiredness(technique.neededStamina)
        updateStats()

        if (!hasNextTechnique())
            updateAction()

        return technique
    }

    fun nextTechniqueInfo() = battleCombination[0]

    fun allTechniquesFrequency(): Double {
        var totalFrequency = 0.0

        battleCombination.forEach { totalFrequency += it.frequency }

        return totalFrequency
    }

    fun allTechniquesStaminaNeeded(): Double {
        var totalStamina = 0.0

        battleCombination.forEach { totalStamina += it.neededStamina }

        return totalStamina
    }

    fun defenseFrom(enemy: Character): DefenseType {
        defendedLastStep = true
        val technique = enemy.nextTechniqueUsing()
        val injury = technique.damage - totalDefense(technique.specification)
        val successDodgeIsPossible = successDodgeIsPossible(technique)
        val successShieldUsingIsPossible = successShieldUsingIsPossible(technique)

        return when (defenseStyle) {
            STAMINA_DURABILITY -> when {
                successDodgeIsPossible -> dodge(enemy, technique)
                successShieldUsingIsPossible -> block(enemy, technique)
                else -> if (onTheKnees)
                    injury(technique, injury * TWO)
                else injury(technique, injury)
            }
            QUICK_DEFENSE -> when {
                shieldUsingDuration() < dodgeDuration() && successShieldUsingIsPossible -> block(enemy, technique)
                successDodgeIsPossible -> dodge(enemy, technique)
                successShieldUsingIsPossible -> block(enemy, technique)
                else -> if (onTheKnees)
                    injury(technique, injury * TWO)
                else injury(technique, injury)
            }
            SHIELD_MAINLY -> when {
                successShieldUsingIsPossible -> block(enemy, technique)
                successDodgeIsPossible -> dodge(enemy, technique)
                else -> if (onTheKnees)
                    injury(technique, injury * TWO)
                else injury(technique, injury)
            }
            DODGE_MAINLY -> when {
                successDodgeIsPossible -> dodge(enemy, technique)
                successShieldUsingIsPossible -> block(enemy, technique)
                else -> if (onTheKnees)
                    injury(technique, injury * TWO)
                else injury(technique, injury)
            }
        }
    }

    private fun successDodgeIsPossible(technique: Technique) = technique.attackDuration > dodgeDuration() &&
            defenseAction - dodgeFrequency() >= 0 && stamina() >= neededStaminaToDodge

    private fun successShieldUsingIsPossible(technique: Technique) = shieldExist() &&
            technique.attackDuration > shieldUsingDuration() && defenseAction - shieldUsingFrequency() >= 0 &&
            stamina() >= neededStaminaForShieldUsing

    private fun dodge(enemy: Character, technique: Technique): DefenseType {
        defenseAction -= dodgeFrequency(ACTUAL)
        increaseTiredness(neededStaminaToDodge)
        updateStats()
        calculateIfOnTheKneesAfter(DODGE, technique)
        updateBattleStyleAgainst(enemy)
        return DODGE
    }

    private fun block(enemy: Character, technique: Technique): DefenseType {
        defenseAction -= shieldUsingFrequency(ACTUAL)
        increaseTiredness(neededStaminaForShieldUsing)
        calculateDefenseStamina(technique)

        val penetration = if (onTheKnees)
            technique.damage * TWO - shieldDefense(technique.specification)
        else
            technique.damage - shieldDefense(technique.specification)

        return if (penetration <= 0) {
            updateStats()
            calculateIfOnTheKneesAfter(BLOCK, technique)
            updateBattleStyleAgainst(enemy)
            BLOCK
        } else {
            injury(technique, penetration)
            updateStats()
            calculateIfOnTheKneesAfter(PARTIAL_BLOCK, technique)
            updateBattleStyleAgainst(enemy)
            PARTIAL_BLOCK
        }
    }

    private fun injury(technique: Technique, injury: Double): DefenseType {
        calculateDefenseStamina(technique)

        return if (injury > 0) {
            increaseInjuries(injury)

            if (!onTheKnees) {
            }
            calculateIfOnTheKneesAfter(FAILURE, technique)

            FAILURE
        } else {
            if (!onTheKnees)
                calculateIfOnTheKneesAfter(ARMOR_DEFENSE, technique)

            ARMOR_DEFENSE
        }
    }

    private fun calculateDefenseStamina(technique: Technique) {
        if (enemyTechniquePushPower(technique) > (strength() - yourBurden()) * suppleness()) {
            val defenseStaminaSpending = enemyTechniquePushPower(technique) - (strength() - yourBurden()) * suppleness()
            when {
                stamina() < defenseStaminaSpending -> increaseTiredness(stamina())
                else -> increaseTiredness(defenseStaminaSpending)
            }
        }
    }

    private fun calculateIfOnTheKneesAfter(defenseType: DefenseType, technique: Technique) {
        if ((stamina(PERCENTAGE) < TEN && strength() - equipmentWeight < enemyBurden(technique.character)) ||
                stamina(PERCENTAGE) < ONE || (defenseType != DODGE && defenseType != DODGE_AND_COUNTERATTACK &&
                enemyTechniquePushPower(technique) > (strength() * TWO - yourBurden()) * suppleness())) {
            onTheKnees = true
            wantToAttack = false
        }
    }

    private fun yourBurden() = when {
        equipmentWeight - strength() < 0 -> 0.0
        else -> equipmentWeight - strength()
    }

    private fun enemyBurden(enemy: Character) = when {
        enemy.equipmentWeight - enemy.strength() < 0 -> 0.0
        else -> enemy.equipmentWeight - enemy.strength()
    }

    private fun enemyTechniquePushPower(technique: Technique) = when {
        technique.type == PRIMARY_WEAPON_ATTACK -> (technique.character.primaryWeapon().weight -
                enemyBurden(technique.character)) * technique.character.quickness()
        technique.type == SECONDARY_WEAPON_ATTACK -> (technique.character.secondaryWeapon().weight -
                enemyBurden(technique.character)) * technique.character.quickness()
        else -> technique.character.strength() * technique.character.quickness() *
        (technique.character.skill(HAND_TO_HAND_FIGHT_SKILL) + technique.character.skill(MAIN_BATTLE_EXPERIENCE))
    }

    private fun updateStats() {
        calculateAttributes()
        calculateHealth()
        calculateStamina()
        calculateSkills()
        calculateEquipmentStats()
        calculateHandToHandStats()
        calculatePrimaryWeaponStats()
        calculateSecondaryWeaponStats()
        calculateShieldStats()
        calculateDodge()
    }

    private fun calculateAttributes() {
        concentration[ACTUAL] = concentration(MAX) - concentration(DEBUFF)
        if (concentration() < ONE) concentration[ACTUAL] = 1.0

        strength[ACTUAL] = strength(MAX) - (suppleness(MAX) / FOUR)
        if (strength() < ONE) strength[ACTUAL] = 1.0

        agility[ACTUAL] = agility(MAX) - (quickness(MAX) / FOUR)
        if (agility() < ONE) agility[ACTUAL] = 1.0

        suppleness[ACTUAL] = suppleness(MAX) - (strength(MAX) / FOUR)
        if (suppleness() < ONE) suppleness[ACTUAL] = 1.0

        quickness[ACTUAL] = quickness(MAX) - (agility(MAX) / FOUR)
        if (quickness() < ONE) quickness[ACTUAL] = 1.0
    }

    private fun calculateHealth() {
        health[MAX] = strength(MAX) * FIVE + FIVE
        health[ACTUAL] = health(MAX) - health(INJURIES) - health(DEBUFF)
        health[PERCENTAGE] = health() / (health(MAX) / HUNDRED)

        if (health() <= 0) {
            health[ACTUAL] = 0.0
            isAlive = false
        }
    }

    private fun calculateStamina() {
        stamina[MAX] = strength(MAX) * ((agility(MAX) + suppleness(MAX) + quickness(MAX)) / THREE) + FIFTEEN
        stamina[ACTUAL] = stamina(MAX) - stamina(TIREDNESS) - stamina(DEBUFF)
        stamina[PERCENTAGE] = stamina() / (stamina(MAX) / HUNDRED)
    }

    private fun calculateSkills() {
        skills[HAND_TO_HAND_FIGHT_SKILL] = (skill(HANDS_FIGHT_SKILL) + skill(FOOTS_FIGHT_SKILL)) / TWENTY

        skills[ONE_HANDED_WEAPON_SKILL] = (skill(SWORD_SKILL) +
                skill(AXE_SKILL) +
                skill(KNIFE_SKILL) +
                skill(MACE_SKILL) +
                skill(HAMMER_SKILL)) / TWENTY

        skills[TWO_HANDED_WEAPON_SKILL] = (skill(TWO_HANDED_SWORD_SKILL) +
                skill(TWO_HANDED_AXE_SKILL) +
                skill(TWO_HANDED_HAMMER_SKILL) +
                skill(TWO_HANDED_MACE_SKILL) +
                skill(STAFF_SKILL) +
                skill(SPEAR_SKILL) +
                skill(HALBERD_SKILL)) / TWENTY

        skills[SHIELD_BLOCK_SKILL] = (skill(LIGHT_SHIELD_SKILL) +
                skill(MIDDLE_SHIELD_SKILL) +
                skill(HEAVY_SHIELD_SKILL)) / TWENTY

        skills[ARMOR_SKILL] = (skill(CLOTH_ARMOR_SKILL) +
                skill(LEATHER_ARMOR_SKILL) +
                skill(CHAIN_ARMOR_SKILL) +
                skill(SCALY_ARMOR_SKILL) +
                skill(LAT_ARMOR_SKILL)) / TWENTY

        skills[MAIN_BATTLE_EXPERIENCE] = (skill(HAND_TO_HAND_FIGHT_SKILL) +
                skill(ONE_HANDED_WEAPON_SKILL) +
                skill(TWO_HANDED_WEAPON_SKILL) +
                skill(SHIELD_BLOCK_SKILL) +
                skill(ARMOR_SKILL)) / TEN
    }

    private fun calculateEquipmentStats() {
        equipmentWeight = 0.0
        equipmentPrice = 0.0

        equipments.forEach { _, v ->
            equipmentPrice += v.price
            equipmentWeight += v.weight
        }

        with(defense) {
            put(CUT, 0.0)
            put(CRUSH, 0.0)
            put(PIERCING, 0.0)
            put(SLASH, 0.0)
            put(BLUNT, 0.0)
        }

        equipments.forEach { _, equipment ->
            if (equipment is Armor) equipment.defense.forEach { spec1, def ->
                defense.replaceAll { spec2, def2 ->
                    if (spec1 == spec2)
                        def2 + (def * ((skill(MAIN_BATTLE_EXPERIENCE) * THREE + skill(ARMOR_SKILL) * TWO + skill(equipment.skillType) + TWENTY) / HUNDRED.toDouble()))
                    else
                        def2
                }
            }
        }
    }

    private fun calculateHandToHandStats() {
        var damage = (TWO * strength() - equipmentWeight) * quickness() * (skill(HAND_TO_HAND_FIGHT_SKILL) + skill(MAIN_BATTLE_EXPERIENCE))
        if (damage < 0) damage = 0.0
        handToHandDamage[BLUNT] = damage
        handToHandCriticalDamage[BLUNT] = handToHandDamage(BLUNT) * (ONE + concentration(ACTUAL) / FIVE)

        neededStaminaForHandToHandAttack = strength() / TEN + yourBurden()

        handToHandActionFrequency[MAX] = ONE / (( strength() + quickness() - equipmentWeight) / FIVE + TWO)

        if (onTheKnees)
            handToHandActionFrequency[MAX] = handToHandActionFrequency(MAX) / TWO

        handToHandAttackDuration[MAX] = handToHandActionFrequency(MAX) / TWO
        handToHandDefenseDuration[MAX] = handToHandActionFrequency(MAX) / FOUR

        handToHandAttackDuration[DEBUFF] = handToHandAttackDuration(MAX) - handToHandAttackDuration(MAX) / HUNDRED * stamina(PERCENTAGE)
        handToHandDefenseDuration[DEBUFF] = handToHandDefenseDuration(MAX) - handToHandDefenseDuration(MAX) / HUNDRED * stamina(PERCENTAGE)

        handToHandActionFrequency[ACTUAL] = handToHandActionFrequency(MAX) - handToHandActionFrequency(DEBUFF)
        handToHandAttackDuration[ACTUAL] = handToHandAttackDuration(MAX) + handToHandAttackDuration(DEBUFF)
        handToHandDefenseDuration[ACTUAL] = handToHandDefenseDuration(MAX) + handToHandDefenseDuration(DEBUFF)
    }

    private fun calculatePrimaryWeaponStats() {
        if (primaryWeaponExist()) {
            primaryWeapon().damage.forEach { k, v ->
                var damage = v * ((strength() - equipmentWeight) / primaryWeapon().weight) * quickness() *
                        (skill(primaryWeapon().skillType) + skill(primaryWeapon().skillGroup) + skill(MAIN_BATTLE_EXPERIENCE))
                if (damage < 0) damage = 0.0
                primaryWeaponDamage.put(k, damage)
                primaryWeaponCriticalDamage.put(k, primaryWeaponDamage(k) * (ONE + concentration(ACTUAL) / FIVE))
            }

            var equipmentBurden = primaryWeapon().weight + equipmentWeight - strength()

            if (equipmentBurden < 0)
                equipmentBurden = 0.0

            neededStaminaForPrimaryWeaponAttack = strength() / TEN + equipmentBurden

            primaryWeaponUsingFrequency[MAX] = ONE / ((strength() + quickness() - primaryWeapon().weight - equipmentWeight) / FIVE + TWO)

            if (onTheKnees)
                primaryWeaponUsingFrequency[MAX] = primaryWeaponUsingFrequency(MAX) / TWO

            primaryWeaponAttackDuration[MAX] = primaryWeaponUsingFrequency(MAX) / TWO
            primaryWeaponDefenseDuration[MAX] = primaryWeaponUsingFrequency(MAX) / FOUR

            if (primaryWeaponUsingFrequency(MAX) < 0) {
                neededStaminaForPrimaryWeaponAttack = 0.0
                primaryWeaponUsingFrequency[MAX] = 0.0
                primaryWeaponAttackDuration[MAX] = 0.0
                primaryWeaponDefenseDuration[MAX] = 0.0
            }

            primaryWeaponAttackDuration[DEBUFF] = primaryWeaponAttackDuration(MAX) - primaryWeaponAttackDuration(MAX) / HUNDRED * stamina(PERCENTAGE)
            primaryWeaponDefenseDuration[DEBUFF] = primaryWeaponDefenseDuration(MAX) - primaryWeaponDefenseDuration(MAX) / HUNDRED * stamina(PERCENTAGE)

            primaryWeaponUsingFrequency[ACTUAL] = primaryWeaponUsingFrequency(MAX) - primaryWeaponUsingFrequency(DEBUFF)
            primaryWeaponAttackDuration[ACTUAL] = primaryWeaponAttackDuration(MAX) + primaryWeaponAttackDuration(DEBUFF)
            primaryWeaponDefenseDuration[ACTUAL] = primaryWeaponDefenseDuration(MAX) + primaryWeaponDefenseDuration(DEBUFF)
        }
    }

    private fun calculateSecondaryWeaponStats() {
        if (secondaryWeaponExist()) {
            secondaryWeapon().damage.forEach { k, v ->
                var damage = v * ((strength() - equipmentWeight) / secondaryWeapon().weight) * quickness() *
                        (skill(secondaryWeapon().skillType) + skill(secondaryWeapon().skillGroup) + skill(MAIN_BATTLE_EXPERIENCE))
                if (damage < 0) damage = 0.0
                secondaryWeaponDamage.put(k, damage)
                secondaryWeaponCriticalDamage.put(k, secondaryWeaponDamage(k) * (ONE + concentration(ACTUAL) / FIVE))
            }

            var equipmentBurden = secondaryWeapon().weight + equipmentWeight - strength()

            if (equipmentBurden < 0)
                equipmentBurden = 0.0

            neededStaminaForSecondaryWeaponAttack = strength() / TEN + equipmentBurden

            secondaryWeaponUsingFrequency[MAX] = ONE / ((strength() + quickness() - secondaryWeapon().weight - equipmentWeight) / FIVE + TWO)

            if (onTheKnees)
                secondaryWeaponUsingFrequency[MAX] = secondaryWeaponUsingFrequency(MAX) / TWO

            secondaryWeaponAttackDuration[MAX] = ONE / secondaryWeaponUsingFrequency(MAX) / TWO
            secondaryWeaponDefenseDuration[MAX] = ONE / secondaryWeaponUsingFrequency(MAX) / FOUR

            if (secondaryWeaponUsingFrequency(MAX) < 0) {
                neededStaminaForSecondaryWeaponAttack = 0.0
                secondaryWeaponUsingFrequency[MAX] = 0.0
                secondaryWeaponAttackDuration[MAX] = 0.0
                secondaryWeaponDefenseDuration[MAX] = 0.0
            }

            secondaryWeaponAttackDuration[DEBUFF] = secondaryWeaponAttackDuration(MAX) - secondaryWeaponAttackDuration(MAX) / HUNDRED * stamina(PERCENTAGE)
            secondaryWeaponDefenseDuration[DEBUFF] = secondaryWeaponDefenseDuration(MAX) - secondaryWeaponDefenseDuration(MAX) / HUNDRED * stamina(PERCENTAGE)

            secondaryWeaponUsingFrequency[ACTUAL] = secondaryWeaponUsingFrequency(MAX) - secondaryWeaponUsingFrequency(DEBUFF)
            secondaryWeaponAttackDuration[ACTUAL] = secondaryWeaponAttackDuration(MAX) + secondaryWeaponAttackDuration(DEBUFF)
            secondaryWeaponDefenseDuration[ACTUAL] = secondaryWeaponDefenseDuration(MAX) + secondaryWeaponDefenseDuration(DEBUFF)
        }
    }

    private fun calculateShieldStats() {
        if (shieldExist()) {
            var equipmentBurden = shield().weight + equipmentWeight - strength()

            if (equipmentBurden < 0)
                equipmentBurden = 0.0

            neededStaminaForShieldUsing = strength() / TEN + equipmentBurden

            shieldUsingFrequency[MAX] = ONE / ((strength() + quickness() - shield().weight - equipmentWeight) / FIVE + TWO)

            if (onTheKnees)
                shieldUsingFrequency[MAX] = shieldUsingFrequency(MAX) / TWO

            shieldUsingDuration[MAX] = ONE / shieldUsingFrequency(MAX) / FOUR

            if (shieldUsingFrequency(MAX) < 0.01) {
                shieldUsingFrequency[MAX] = 0.0
                shieldUsingDuration[MAX] = 0.0
            }

            shieldUsingDuration[DEBUFF] = shieldUsingDuration(MAX) - shieldUsingDuration(MAX) / HUNDRED * stamina(PERCENTAGE)

            shieldUsingFrequency[ACTUAL] = shieldUsingFrequency(MAX) - shieldUsingFrequency(DEBUFF)
            shieldUsingDuration[ACTUAL] = shieldUsingDuration(MAX) + shieldUsingDuration(DEBUFF)
        }
    }

    private fun calculateDodge() {
        neededStaminaToDodge = strength() / TEN + yourBurden()

        if (neededStaminaToDodge < 0.1)
            neededStaminaToDodge = 0.1

        dodgeFrequency[MAX] = ONE / ((agility() + quickness() - yourBurden()) / FIVE + ONE + (concentration(ACTUAL) / TWO))

        if (onTheKnees)
            dodgeFrequency[MAX] = dodgeFrequency(MAX) / TWO

        dodgeDuration[MAX] = ONE / dodgeFrequency(MAX) / TWO

        if (dodgeFrequency(MAX) < 0.01) {
            dodgeFrequency[MAX] = 0.0
            dodgeDuration[MAX] = 0.0
        }

        dodgeDuration[DEBUFF] = dodgeDuration(MAX) - dodgeDuration(MAX) / HUNDRED * stamina(PERCENTAGE)

        dodgeFrequency[ACTUAL] = dodgeFrequency(MAX) - dodgeFrequency(DEBUFF)
        dodgeDuration[ACTUAL] = dodgeDuration(MAX) + dodgeDuration(DEBUFF)
    }

    fun newStep() {
        updateAction()
        updateStamina()
        updateStats()

        attackedLastStep = false
        defendedLastStep = false
    }

    fun newStep(enemy: Character) {
        updateAction()
        updateStamina()
        updateBattleStyleAgainst(enemy)
        updateStats()

        attackedLastStep = false
        defendedLastStep = false
    }

    private fun updateAction() {
        if (attackAction < ONE)
            attackAction += ONE

        if (defenseAction < ONE)
            defenseAction += ONE
    }

    private fun updateStamina() {
        val newTiredness = if (!onTheKnees)
            stamina(TIREDNESS) - stamina(MAX) / HUNDRED * (health(PERCENTAGE) / HUNDRED / TWO)
        else
            stamina(TIREDNESS) - stamina(MAX) / HUNDRED * (health(PERCENTAGE) / HUNDRED)

        if (newTiredness < 0)
            stamina[TIREDNESS] = 0.0
        else
            stamina[TIREDNESS] = newTiredness

        if (!attackedLastStep && !defendedLastStep) {
            if (stamina(DEBUFF) > 0 && onTheKnees)
                stamina[DEBUFF] = stamina(DEBUFF) - stamina(MAX) / HUNDRED
            else if (stamina(DEBUFF) > 0)
                stamina[DEBUFF] = stamina(DEBUFF) - stamina(MAX) / HUNDRED / TWO
            else
                stamina[DEBUFF] = 0.0
        }

        if (stamina(PERCENTAGE) > TWENTY && !attackedLastStep && !defendedLastStep || stamina(PERCENTAGE) > THIRTY)
            onTheKnees = false
    }

    private fun updateBattleStyleAgainst(enemy: Character) {
        updateAttackStyleAgainst(enemy)
        updateDefenseStyleAgainst(enemy)
        updateTechniquesAgainst(enemy)
    }

    private fun updateAttackStyleAgainst(enemy: Character) {
        if (ai)
            attackStyle = AI.calculatingYourAttackStyle(this, enemy)

        wantToAttack = when (attackStyle) {
            AGGRESSIVE_OFFENSIVE -> !onTheKnees
            FORCING -> enemy.stamina() <= enemy.stamina(MAX) / HUNDRED * EIGHTY && stamina() >= stamina(MAX) / HUNDRED * TEN && !onTheKnees
            MODERATE -> enemy.stamina() <= enemy.stamina(MAX) / HUNDRED * FIFTY && stamina() >= stamina(MAX) / HUNDRED * THIRTY && !onTheKnees
            CAREFUL -> enemy.stamina() <= enemy.stamina(MAX) / HUNDRED * THIRTY && stamina() >= stamina(MAX) / HUNDRED * FIFTY && !onTheKnees
            DEEP_DEFENSE -> enemy.stamina() <= enemy.stamina(MAX) / HUNDRED * THIRTY && stamina() >= stamina(MAX) / HUNDRED * EIGHTY && !onTheKnees
        }
    }

    private fun updateDefenseStyleAgainst(enemy: Character) {
        if (ai)
            defenseStyle = AI.calculatingYourDefenseStyle(this, enemy)
    }

    private fun updateTechniquesAgainst(enemy: Character) {
        battleCombination.clear()

        if (ai)
            AI.calculatingYourTechniques(this, enemy)
    }
}