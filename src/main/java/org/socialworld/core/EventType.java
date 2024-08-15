/*
* Social World
* Copyright (C) 2015  Mathias Sikos
*
* This program is free software; you can redistribute it and/or
* modify it under the terms of the GNU General Public License
* as published by the Free Software Foundation; either version 2
* of the License, or (at your option) any later version.
*
* This program is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with this program; if not, write to the Free Software
* Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.  
*
* or see http://www.gnu.org/licenses/gpl-2.0.html
*
*/
package org.socialworld.core;


/**
 * @author Mathias Sikos
 *
 */
public enum EventType {

	nothing(0),
	
//////////////////////////////////////////////////////////////////////
//EventToCauser	(event with influence to causer itself)
//////////////////////////////////////////////////////////////////////

	selfSleep(1), selfDrink(2), selfEat(3), selfPiss(4), selfShit(5), 	
	
	selfMoveWalk(8), selfMoveRun(9), selfMoveSneak(10), selfMoveJump(11), selfMoveSwim(12), selfMoveFly(13),
	
	selfExamineByLook(16), selfExamineBySmell(17), selfExamineByTaste(18), selfExamineByTouch(19),
	
	selfTouchByHand(24), selfTouchByFoot(25),
	
	selfInventoryTake(32), selfInventoryDrop(33), selfInventorySwitch(34), selfInventorySet(35), selfInventoryGet(36),
	
	selfHandleItemUse2(40), selfHandleItemUseLeft(41), selfHandleItemUseRight(42), selfHandleItemAddRtoL(43), selfHandleItemAddLtoR(44), selfHandleItemPull(45), selfHandleItemPush(46),
	
	selfWeaponLeftStab(48), selfWeaponLeftStroke(49), selfWeaponLeftBackhand(50), selfWeaponRightStab(51), selfWeaponRightStroke(52), selfWeaponRightBackhand(53), selfWeaponClub(54),

	selfPunchLeftFistStraight(56), selfPunchLeftFistSideways(57), selfPunchLeftFistUpward(58), selfPunchRightFistStraight(59), selfPunchRightFistSideways(60), selfPunchRightFistUpward(61),
	
	selfListenToStatement(64), selfListenToQuestion(65), selfListenToInstruction(66), selfUnderstand(67), 
	
	selfAskNormal(72), selfAskScream(73), selfAskWhisper(74), selfAnswerNormal(75), selfAnswerScream(76), selfAnswerWhisper(77), 
	
	selfSayNormal(80), selfSayScream(81), selfSayWhisper(82),

//////////////////////////////////////////////////////////////////////
//EventToTargets	(event with influence to explicit involved objects (targets, items ...)
//////////////////////////////////////////////////////////////////////
	
	/*targetSleep(129),*/ targetDrink(130), targetEat(131), /*targetPiss(132), targetShit(133), */	
	
	/*targetMoveWalk(136), targetMoveRun(137), targetMoveSneak(138), targetMoveJump(139), targetMoveSwim(140), targetMoveFly(141), */
	
	targetExamineByLook(144), targetExamineBySmell(145), targetExamineByTaste(146), targetExamineByTouch(147),
	
	targetTouchByHand(152), targetTouchByFoot(153),
	
	targetInventoryTake(160), targetInventoryDrop(161), targetInventorySwitch(162), targetInventorySet(163), targetInventoryGet(164),
	
	targetHandleItemUse2(168), targetHandleItemUseLeft(169), targetHandleItemUseRight(170), targetHandleItemAddRtoL(171), targetHandleItemAddLtoR(172), targetHandleItemPull(173),targetHandleItemPush(174),
	
	targetWeaponLeftStab(176), targetWeaponLeftStroke(177), targetWeaponLeftBackhand(178), targetWeaponRightStab(179), targetWeaponRightStroke(180), targetWeaponRightBackhand(181), targetWeaponClub(182),

	targetPunchLeftFistStraight(184),  targetPunchLeftFistSideways(185), targetPunchLeftFistUpward(186), targetPunchRightFistStraight(187),  targetPunchRightFistSideways(188), targetPunchRightFistUpward(189),

	/*targetListenToStatement(192), targetListenToQuestion(193), targetListenToInstruction(194), targetUnderstand(195),*/
	
	targetAskNormal(200), targetAskScream(201), targetAskWhisper(202), targetAnswerNormal(203), targetAnswerScream(204), targetAnswerWhisper(205),
	
	/*targetSayNormal(208), targetSayScream(209), targetSayWhisper(210),*/

//////////////////////////
//EventToCandidates	
//////////////////////////

	candidatesSleep(257), candidatesDrink(258), candidatesEat(259), candidatesPiss(260), candidatesShit(261), 	
	
	candidatesMoveWalk(264), candidatesMoveRun(265), candidatesMoveSneak(266), candidatesMoveJump(267), candidatesMoveSwim(268), candidatesMoveFly(269),
	
	candidatesExamineByLook(272), candidatesExamineBySmell(273), candidatesExamineByTaste(274), candidatesExamineByTouch(275),
	
	candidatesTouchByHand(280), candidatesTouchByFoot(281),
	
	candidatesInventoryTake(288), candidatesInventoryDrop(289), candidatesInventorySwitch(290), candidatesInventorySet(291), candidatesInventoryGet(292),
	
	candidatesHandleItemUse2(296), candidatesHandleItemUseLeft(297), candidatesHandleItemUseRight(298), candidatesHandleItemAddRtoL(299), candidatesHandleItemAddLtoR(300), candidatesHandleItemPull(301),candidatesHandleItemPush(302),
	
	candidatesWeaponLeftStab(304), candidatesWeaponLeftStroke(305), candidatesWeaponLeftBackhand(306), candidatesWeaponRightStab(307), candidatesWeaponRightStroke(308), candidatesWeaponRightBackhand(309), candidatesWeaponClub(310),
	
	candidatesPunchLeftFistStraight(312),  candidatesPunchLeftFistSideways(313), candidatesPunchLeftFistUpward(314), candidatesPunchRightFistStraight(315),  candidatesPunchRightFistSideways(316), candidatesPunchRightFistUpward(317),
	
/*	candidatesListenToStatement(320), candidatesListenToQuestion(321), candidatesListenToInstruction(322), candidatesUnderstand(323),*/

/*	candidatesAskNormal(328), candidatesAskScream(329), candidatesAskWhisper(330), candidatesAnswerNormal(331), candidatesAnswerScream(332), candidatesAnswerWhisper(333),*/
	
	candidatesSayNormal(336), candidatesSayScream(337), candidatesSayWhisper(338),

//////////////////////////
//EventToPercipient	
//////////////////////////

	percipientSleep(385), percipientDrink(386), percipientEat(387), percipientPiss(388), percipientShit(389), 	

	percipientMoveWalk(392), percipientMoveRun(393), percipientMoveSneak(394), percipientMoveJump(395), percipientMoveSwim(396), percipientMoveFly(397),

	percipientExamineByLook(400), percipientExamineBySmell(401), percipientExamineByTaste(402), percipientExamineByTouch(403),

	percipientTouchByHand(408), percipientTouchByFoot(409),

	percipientInventoryTake(416), percipientInventoryDrop(417), percipientInventorySwitch(418), percipientInventorySet(419), percipientInventoryGet(420),

	percipientHandleItemUse2(424), percipientHandleItemUseLeft(425), percipientHandleItemUseRight(426), percipientHandleItemAddRtoL(427), percipientHandleItemAddLtoR(428), percipientHandleItemPull(429),percipientHandleItemPush(430),

	percipientWeaponLeftStab(432), percipientWeaponLeftStroke(433), percipientWeaponLeftBackhand(434), percipientWeaponRightStab(435), percipientWeaponRightStroke(436), percipientWeaponRightBackhand(437), percipientWeaponClub(438),

	percipientPunchLeftFistStraight(440),  percipientPunchLeftFistSideways(441), percipientPunchLeftFistUpward(442), percipientPunchRightFistStraight(443),  percipientPunchRightFistSideways(444), percipientPunchRightFistUpward(445),

	/*percipientListenToStatement(448), percipientListenToQuestion(449), percipientListenToInstruction(450), percipientUnderstand(451),*/

	percipientAskNormal(456), percipientAskScream(457), percipientAskWhisper(458), percipientAnswerNormal(459), percipientAnswerScream(460), percipientAnswerWhisper(461),

	/*percipientSayNormal(464), percipientSayScream(465), percipientSayWhisper(466), */
	
	percipientExists(511);
	
	public static final int MAX_EVENT_TYPE =  512;

	private int index;

	private EventType(int index) {
		this.index = index;
	}

	/**
	 * The method returns the event type name which belongs to the parameter  index.
	 * 
	 * @param index
	 *            event type index
	 * @return event type name
	 */
	public static EventType getEventType(int index) {
		for (EventType type : EventType.values())
			if (type.index == index)
				return type;
		return nothing; // instead of null
	}
	
	public static EventType fromName(String name) {
		for (EventType type : EventType.values())
			if (type.toString().equals(name))
				return type;
		return null;
	}

	/**
	 * The method returns the index of event type.
	 * 
	 * @return event type index
	 */
	public int getIndex() {
		return index;
	}

	public boolean isEventToCauserItself() {
		
		if (this.index > 0  & this.index < 128) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean isEventToTarget() {
		
		if (this.index > 128  & this.index < 256) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean isEventToPercipient() {
		
		if (this.index > 384  & this.index < 512) {
			return true;
		}
		else {
			return false;
		}
	}

	public boolean isRelevantForChangingPosition() {
		
		// selfMoveWalk(8), selfMoveRun(9), selfMoveSneak(10), selfMoveJump(11), selfMoveSwim(12), selfMoveFly(13),
		if (this.index >= 8 || this.index <= 13) return true;
		
		// targetInventoryTake(160), targetInventoryDrop(161), targetInventorySwitch(162), targetInventorySet(163), targetInventoryGet(164),
		if (this.index >= 160 || this.index <= 164) return true;
		
		// targetHandleItemUse2(168), targetHandleItemUseLeft(169), targetHandleItemUseRight(170), targetHandleItemAddRtoL(171), targetHandleItemAddLtoR(172), targetHandleItemPull(173),targetHandleItemPush(174),
		if (this.index >= 168 || this.index <= 174) return true;
		
		// targetWeaponLeftStab(176), targetWeaponLeftStroke(177), targetWeaponLeftBackhand(178), targetWeaponRightStab(179), targetWeaponRightStroke(180), targetWeaponRightBackhand(181), targetWeaponClub(182),
		if (this.index >= 176 || this.index <= 182) return true;

		// targetPunchLeftFistStraight(184),  targetPunchLeftFistSideways(185), targetPunchLeftFistUpward(186), targetPunchRightFistStraight(187),  targetPunchRightFistSideways(188), targetPunchRightFistUpward(189),
		if (this.index >= 184 || this.index <= 189) return true;

		
		
		// candidatesMoveWalk(264), candidatesMoveRun(265), candidatesMoveSneak(266), candidatesMoveJump(267), candidatesMoveSwim(268), candidatesMoveFly(269),
		if (this.index >= 264 || this.index <= 269) return true;

		// candidatesWeaponLeftStab(304), candidatesWeaponLeftStroke(305), candidatesWeaponLeftBackhand(306), candidatesWeaponRightStab(307), candidatesWeaponRightStroke(308), candidatesWeaponRightBackhand(309), candidatesWeaponClub(310),
		if (this.index >= 304 || this.index <= 310) return true;
		
		// candidatesPunchLeftFistStraight(312),  candidatesPunchLeftFistSideways(313), candidatesPunchLeftFistUpward(314), candidatesPunchRightFistStraight(315),  candidatesPunchRightFistSideways(316), candidatesPunchRightFistUpward(317),
		if (this.index >= 312 || this.index <= 317) return true;
		
				
		return false;
	}
	

	public boolean isRelevantForEffectiveCheck() {
		
		// pull and push with effective check
		if (this.index == 173 || this.index == 174) return true;
		// touch with effective check
		if (this.index == 152 || this.index == 153) return true;
		// talk with effective check
		if (this.index >= 200 && this.index <= 205) return true;
		
		// the following with noeffective check
		// all events to causer itself
		if (this.index <= 128) return false;
		// all events to percipients
		if (this.index >= 385 && this.index <= 511) return false;
		// all events to explicit targets (instead defined above)  (there is no check needed, because there IS!!! an effect (it's an event to target !!!) 
		if (this.index >= 129 && this.index <= 256) return false;
		// there are no candidates for body functions
		if (this.index >= 257 && this.index <= 263) return false;
		// there are no candidates for examine actions
		if (this.index >= 272 & this.index <= 279) return false;
		// there are no percipients for hearing
		if (this.index >= 448 & this.index <= 455) return false;
		// there are no percipients for saying (because every percipient is a candidate)
		if (this.index >= 464 & this.index <= 471) return false;

		// the rest with effective check
		// nearly all events to candidates
		return true;
		
	}
	
	public float getEffectDistance() {
		//return 1000000.0F;
		
		switch (this) {
		// TODO getEffectDistance()
		case candidatesMoveWalk: return 5000.0F;
		case candidatesMoveRun: return 5000.0F;
		case candidatesMoveSneak: return 1000.0F; 
		case candidatesMoveJump: return 3000.0F;
		case candidatesMoveSwim: return 3000.0F;
		case candidatesMoveFly:  return 5000.0F;
			
		case candidatesTouchByHand: return 1000.0F; 
		case candidatesTouchByFoot: return 1000.0F; 
				
		case candidatesInventoryGet:
		case candidatesInventorySet:
		case candidatesInventoryTake:
		case candidatesInventoryDrop:
		case candidatesInventorySwitch:
			return 1000.0F;
			
		case candidatesHandleItemUse2: 
		case candidatesHandleItemUseLeft:
		case candidatesHandleItemUseRight:
		case candidatesHandleItemAddRtoL:
		case candidatesHandleItemAddLtoR:
		case candidatesHandleItemPull:
		case candidatesHandleItemPush:
			return 1000.0F; 
			
		case candidatesWeaponLeftStab:
		case candidatesWeaponLeftStroke:
		case candidatesWeaponLeftBackhand:
		case candidatesWeaponRightStab:
		case candidatesWeaponRightStroke:
		case candidatesWeaponRightBackhand:
		case candidatesWeaponClub:
			return 2000.0F; 
			
		case candidatesPunchLeftFistStraight:
		case candidatesPunchLeftFistSideways:
		case candidatesPunchLeftFistUpward:
		case candidatesPunchRightFistStraight:
		case candidatesPunchRightFistSideways:
		case candidatesPunchRightFistUpward:
			return 1000.0F; 
			
					
		case candidatesSayNormal: return 10000.0F; 
		case candidatesSayScream: return 40000.0F; 
		case candidatesSayWhisper: return 1000.0F; 

		case percipientExists: return 5000.0F;
			
		default:
			return 10000.0F;
		}
		
	}

	public float getEffectAngle() {
		switch (this) {
		
		case candidatesMoveWalk:
		case candidatesMoveRun:
		case candidatesMoveSneak:
		case candidatesMoveJump:
		case candidatesMoveSwim:
		case candidatesMoveFly:
			return 45.0F;
			
		case candidatesTouchByHand: 
		case candidatesTouchByFoot:
			return 45.0F;

		case candidatesInventoryGet:
		case candidatesInventorySet:
		case candidatesInventoryTake:
		case candidatesInventoryDrop:
		case candidatesInventorySwitch:
			return 360.0F;
			
		case candidatesHandleItemUse2:
		case candidatesHandleItemUseLeft:
		case candidatesHandleItemUseRight:
		case candidatesHandleItemAddRtoL:
		case candidatesHandleItemAddLtoR:
		case candidatesHandleItemPull:
		case candidatesHandleItemPush:
			return 360.0F;

		case candidatesWeaponLeftStab:
		case candidatesWeaponLeftStroke:
		case candidatesWeaponLeftBackhand:
		case candidatesWeaponRightStab:
		case candidatesWeaponRightStroke:
		case candidatesWeaponRightBackhand:
		case candidatesWeaponClub:
			return 45.0F;

		case candidatesPunchLeftFistStraight:
		case candidatesPunchLeftFistSideways:
		case candidatesPunchLeftFistUpward:
		case candidatesPunchRightFistStraight:
		case candidatesPunchRightFistSideways:
		case candidatesPunchRightFistUpward:
			return 45.0F;
				
		case candidatesSayNormal:
		case candidatesSayScream:
		case candidatesSayWhisper:
			return 360.0F;
		
		case percipientExists:
			return 0.0F;
			
		default:
			return 0.0F;
		}
	}
	
}

/*
nothing(0),

sleep(1), drink(2), eat(3), piss(4), shit(5), 	

moveWalk(8), moveRun(9), moveSneak(10), moveJump(11), moveSwim(12), moveFly(13),

examineByLook(16), examineBySmell(17), examineByTaste(18), examineByTouch(19),

touchByHand(24), touchByFoot(25),

inventoryTake(32), inventoryDrop(33), inventorySwitch(34), inventorySet(35), inventoryGet(36),

handleItemUse2(40), handleItemUseLeft(41), handleItemUseRight(42), handleItemAddRtoL(43), handleItemAddLtoR(44), handleItemPull(45), handleItemPush(46),

weaponLeftStab(48), weaponLeftStroke(49), weaponLeftBackhand(50), weaponRightStab(51), weaponRightStroke(52), weaponRightBackhand(53), weaponClub(54),

punchLeftFistStraight(56),  punchLeftFistSideways(57), punchLeftFistUpward(58), punchRightFistStraight(59),  punchRightFistSideways(60), punchRightFistUpward(61),

listenToStatement(64), listenToQuestion(65), listenToInstruction(66), understand(67),

askNormal(72), askScream(73), askWhisper(74), answerNormal(75), answerScream(76), answerWhisper(77),

sayNormal(80), sayScream(81), sayWhisper(82),
*/

