import React, { useState } from 'react';
import Header from './header/Header';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';

function App() {
  return (
    <Router>
      <Header />
      <div className="test">
        무언가 매우 긴 문장들
        스크롤 테스트

        He must have had a life before this. A mother, a father, a home. Maybe sisters, or brothers. But it had been so long—too long—and now all he knew was this bloody game. His hands knew no other shape than fists curled tightly around a sword, swinging eternally, finding its mark through skin and bone.

They all tried to run, of course. They built walls and cowered in corners, but he always found them. Sometimes, they begged. Sometimes, they chose to jump from cliffs rather than face his reckoning. And sometimes, they stared back at him with eyes as empty as his own and welcomed death with open arms. Those were the ones he envied the most.

Technoblade never dies, they whispered around campfires and funeral pyres.

He prayed that that wasn’t true.

The voices led him to kingdoms and shires and towns—it didn’t matter what they offered him in return; the voices didn’t demand coin, they demanded blood. He fought for bold men and stupid men, greedy kings and starry-eyed rebels. He fought for armies doomed to fail and dragged them into the light of glory. He had lost count of how many allies he’d fought beside—after a time, their names and faces had faded into the recesses of his hazy memory.

And then there was the Angel of Death.

He was one of the very few people with a reputation that matched Technoblade’s. He’d heard of the angel through whispered stories and snatches of tavern gossip. I heard he has obsidian wings, one patron would say to another over a cup of ale. I heard he once massacred an entire army, all by himself. He makes even the Green God afraid.

Technoblade had begun to imagine a ruthless man—an immortal butcher with the same wretched grin as his. But Philza was not an avenging angel. He was just Philza.

They’d met by coincidence, in a land of ice and snow. It was barren, but they’d made quick work of it, together—first as allies and then as friends. Through it all, Philza had smiled instead of grinned, laughed instead of cackled. On calmer days, they’d wile away time with tea and chess, and silent meditations that quieted the screaming in Techno’s head, if only for a little while.

“You know,” Techno had said during one of their sparring matches (they had to stay in shape, of course, because peacetimes never lasted as long as people hoped), “the stories never talk about this side of you.”

Philza had paused, a small, amused smile on his face. “Oh?” he’d said. “What do the stories talk about, then?”

“They call you the Angel of Death.” Techno dug his heels in as Philza resumed an onslaught of blows with his dulled sword. “They said you leave a path of destruction in your wake that nothing—ha!” Techno parried and went on the offensive. “—that nothing is sacred to you.”

Their blades met. They pushed against each other, trying to gain an upper hand, and it was only because they were standing so close that Techno noticed the shift in Philza’s eyes: a momentary coldness that was as brutal as the blizzard raging outside. It was there and gone in an instant. Light returned, and Philza laughed as he pushed back against Techno’s sword.

“Stories are curious things,” Philza said as he swung again, barely giving Techno time to dodge. “Some of them are true…”

He moved so quickly. Techno could do nothing but stand there as Philza rushed him with a hilt to the ribs, knocking Techno backwards onto the training room floor. Techno scrambled to his knees, but Philza was already standing over him with his sword held high above his head, his eyes glimmering with an emotion Techno couldn’t place. For once in his immortal life, kneeling there in front of the first person he called friend, Technoblade felt hunted.

And then Philza lowered his weapon. He smiled gently down at Techno—the soft smile Techno was used to—and offered Techno a gloved hand.

“… and some of them are not,” Philza finished. “So. Best of two out of three?”

“You’re a bastard,” Techno said playfully, even as the voices screamed, run, run, run. He took Philza’s offered hand and pulled himself up beside the man that he was sure could have cut him in two, no matter how dulled the sword’s edge was. As Philza patiently moved Techno through all the things he’d done wrong (small things like foot placement and his hilt grip being an inch off), Techno found it equal parts amusing and frightening that despite his eons of bloody fighting, it took only a few minutes of sparring for Philza to find flaws in his technique. But then again, Techno’s technique wasn’t particularly polished; it took only one brutal swing to fell most people. Something told him that Philza would be harder to kill than that.

They conquered nations, he and his golden-haired friend. They were bathed in glory, twin gods shining in the middle of a bloody fields. But as their empire grew, so did their enemies. They came in droves, day after day, and before long Techno had forgotten what peace tasted like. The days were long and the nights were longer; every flicker of movement was a spy in the shadows, every ally was a potential traitor, every word was a declaration of war. Their home had become a target for a thousand armies.

Through it all, his one constant was Philza—until he wasn’t. Technoblade simply looked up one day from a map detailing enemy lines and realized he’d been talking to empty air. He had no idea how long he’d been alone, sitting in a dusty library with stale tea untouched in the corner. He had no idea if Philza ever said he was leaving, or if he simply went as he arrived suddenly, swiftly, like a snowstorm.

Afterwards, there was hardly any point in maintaining the empire. The voices were getting bored, anyway. They wanted more. A fresh fight, more stories. So Techno took his sword and his shield, and abandoned ship. He’d done it a million times before, but the thought of a chess board lying unused in a crumbling castle made him feeling something close to regret.

Technoblade wandered the world, trying to appease the voices. Neither of them were ever satisfied. No matter how much chaos he dealt, there was always more work to be done. So he worked. He had no idea for how long. All he remembered from that bloody time was a sense of unfulfillment, like a story had been left unfinished halfway through. Years. Decades. Maybe more. It hardly mattered.

In the end, he knew, it would all be the same. The world would end, and he would remain—always fighting, always alone.

He didn’t know what brought him to the kingdom in the first place. Did he really have to see it for himself? Was it simply to satiate his curiosity? Was he bored? Or did he hear of a kingdom untouched by the wars and petty grudges of its neighbors—keeping its peace and neutrality for a century—and take it as a challenge? Whatever it was, when Technoblade stood under the shadow of a gilded castle, watching its flags flutter lazily in the summer breeze, he felt a flicker of a once-familiar emotion stir in his heart. There was something about the cobblestone walls and towers rising towards the sky that reminded him of a different palace, somewhere cold and far away.

“Hello, stranger!” one of the guards at the gates called out. “You sightseeing?”

Technoblade paused at the man’s cheerful tone. Most of the guards that caught sight of Techno’s sword and blood-red cape were quick to draw their weapons, but aside from spears that seemed more decorative than threatening, the guards at the gates didn’t seem to be on guard at all. Hubris, the voices said, this is a kingdom of hubris.

“Perhaps,” Techno drawled, indulging the guard. “Although, I suppose I’m more curious about the inside, rather than the outside.”

“Why didn’t you just say so!” The guard beckoned Techno forwards. “The castle is always open for tourists. Just come right in!”

That was how Techno found himself walking leisurely down the halls of a castle that, under normal circumstances, he would have been storming, blades drawn. The guards did draw the line at his weaponry, and made him discard his swords at the door—as if Technoblade needed more than his hands (and sometimes, not even those) to wreak havoc. The castle’s laxness in security was disproportional to the opulence within: lush carpet softened Techno’s footsteps, elegant tapestries decorated the walls, flowers bloomed from vases as tall as him, and oil paintings in gilded frames. Paintings of solemn landscapes, of wild animals roaming a cultivated garden, of a dark-haired boy astride a white horse, a hint of a smile in the corner of his mouth, and of the king—

Technoblade stopped under the painting, nestled between vases of irises. Oh, he thought. That’s why. It wasn’t hubris making this kingdom think they were protected from everything. It was their king.

Rendered in paint and shadow, he looked just as Technoblade remembered, the years leaving no mark on his immortal face. He was standing behind a modest throne, his hand laid gently on the shoulder of a dark-haired woman that must be his queen. In the queen’s arms was a golden-haired toddler, sleeping peacefully. On the floor by her feet, with his legs crossed under him, was another child, older, with a gold circlet nestled in his brown curls.

“Wilby!”

A child’s shrill voice rang down the hall. Technoblade’s hand itched instinctively for his sword as he turned from the painting and found himself facing the very same boy from the painting.

The prince. He was a tall, lean thing, his face still holding the faint traces of boyhood. He couldn’t have been more than fourteen. In the painting, he’d been grinning, forever immortalized in delight. But here, he was staring, his dark eyes unnaturally focused, as if Techno was a particularly interesting book he was quietly picking apart in his head. Techno had seen that expression many times in the faces of wizened generals looking over battlefield arrangements.

“Hullo,” the prince said cautiously.

Technoblade found himself raising his hand in a small wave. “Hello.”

“Wilby! Wait for me!” the first voice called again, closer this time, and heralding the appearance of another child around the bend of the hallway. By his lavish attire and the small army of servants following fretfully after him, this could only be the younger prince, barely more than a babe in the painting but now a rather loud six-year-old.

The younger prince marched purposefully towards his older brother—Wilby?—and clung decidedly to his side as they both stared up at Techno.

“And who are you?” the small prince said, in what he must have intended to be a threatening tone. But he sounded only like he really was: a child.

“A visitor,” said Techno, unsure of what he was meant to say now.

“Have you come to have an audience with our father?” the older prince asked in a decidedly more level tone.

“You can’t,” the younger prince snapped at once, tightening his hold on his older brother’s shirtfront. “Dad promised today was our day with him, so you can just leave now, thank you!”

“Tommy, calm yourself.”

“But Wilbur, Dad said—”

“I know what Father said, Tommy.” The older prince—Wilbur, then, not Wilby; gods know what Techno would have said and done if the man had truly named his son Wilby—was still staring at Techno like a vulture waiting for a dying animal to drop. “So, visitor, what is your business here?”

“I have no business,” Technoblade said. “I am visiting. Sightseeing. I’m a traveler.”

“First you are a visitor and now you are a traveler.” A smile tugged at the prince’s lips. “This exchange would be much easier if we knew your name.”

Technoblade glanced at the servants lining the hall behind the princes, clearly in earshot but dutifully maintaining the illusion of privacy. But if he knew their father at all, he’d know that most of those standing guard around his sons would be lethal killers—he just hadn’t anticipated the arrival of a god. What would they do if they heard his name? Would any of them recognize it? Would they know what it meant to have him stand before their young princes? How long would they last against him?

As he looked down at the two brothers, he found them small. He could crush them under the heel of his boot like ants.

But instead, Techno found himself saying, “My name is—”

“Technoblade?”

Technoblade lifted his eyes from the young princes and found himself staring at their father.

“Philza?”

Philza stood at the end of the hallway, undoubtedly following the familiar cadence of his sons’ voices. He glanced at them now, still standing before Technoblade like unwitting sheep waiting for slaughter. But Philza’s eyes showed no fear. Instead, when he looked back at Techno, he only smiled, his face softening with a familiar relief—the expression of a man after a long, hard-fought war, seeing peace on the horizon at last.

“Old friend,” said Philza. “It’s nice to see you again.”

Traitor, the voices clamored, traitor traitor traitor traitor—

“Father!” Wilbur’s voice brought them back to reality; this was a different castle, a different time. “Do you know this stranger?”

“Well, obviously, Wilbur.” Tommy rolled his eyes. “Dad just said his name, didn’t he? Technoblade. That’s a dumb name.”

“Tommy!” Philza reprimanded, with no real heat behind his words. He drew closer to them, his steps quiet and even. The servants that had followed the two boys bowed in deference to their liege, despite him wearing no crown. In fact, he looked just as much as a traveler as Techno was—dressed in a simple trousers and shirt, perfect for blending in, perfect for a man on the run.

“It’s been a long time,” Philza said when he reached them, putting a gentle hand on the top of Tommy’s blond head. The boy arched towards the touch like a sunflower reaching towards the sun. Technoblade didn’t know if the move was calculated, or just a simple act of affection. Or, knowing Philza, both. “How have you been?”

“How have I been?” Techno repeated numbly, feeling a familiar chill creep into his bones. “Phil, I—”

“Actually,” Philza interrupted, before kneeling to look his boys in the eyes. “Wilbur, take your brother out to the garden for a bit, yeah?”

Wilbur pouted, for once looking like a boy his age. “But you said—”

“I know what I promised, and I keep my promises, don’t I?” Philza ruffled Wilbur’s hair and then Tommy’s. “I’ll join you in a moment. I just need to have a talk with Technoblade here.”

Wilbur stared at his father for a long moment, as if weighing the truth of his words, before nodding. He took his brother’s hand in his and began leading him away. “C’mon, Tommy,” he said. “Let’s play outside.”

“Technoblade’s still a dumb name,” Tommy muttered as they passed him, closely followed by their servants.

Wilbur met Technoblade’s eyes, just for a moment, before they were gone—down the hall, out of sight, leaving Technoblade alone with the king. Technoblade turned towards Philza, his old friend, and found the smile wiped clean from his face.

Philza gestured down the hall. “Walk with me?”

Technoblade could only nod, and follow Philza.

They were quiet as they walked. Techno remembered days like these during their time together, long days of companionable silence as they simply existed together. But there was something different this time. There was an edge. Techno could sense Philza sizing him up, tallying his hidden weapons, calculating his improvements. In turn, Techno mapped his escape routes as Philza led him through the halls, then up a sweeping flight of stairs. He did not want to expect violence from Philza, but he hadn’t expected to be left behind, either.

They reached a balcony overlooking a garden, where most of the flowers indoors undoubtedly came from. Wisteria and ivy grew around marble pillars; rosebushes and dandelions and carnations bloomed en masse at the foot of elaborate stone statues. At the center of the garden was a weeping willow, its branches providing shade for the two boys chasing each other across the grass. Their laughter echoed through the glade, reaching even Techno and their father high up on the balcony.

For a while, the two of them just watched the two princes. Wilbur was obviously faster than Tommy, but he slowed his pace just enough for his little brother to have fun chasing his heels.

“They’re a handful.” Philza’s soft tone turned Techno’s attention away from the princes. The king was almost smiling, but the hard glint in his eyes didn’t disappear. “Wilbur was a quieter, before Tommy was born. A little bookworm, holed up in his room all day. But I have a feeling you didn’t drop by for silly stories like that.” Philza turned towards Techno. “So, go ahead. Let me have it.”

Techno didn’t know what he was meant to feel. He didn’t know what he was meant to say. For years, he’d put Philza out of his mind, determined to forget that interlude of peace. He’d let the memories fester like untreated wounds, and now he thought he’d rather die of the infection than acknowledge out loud that it was real, that the pain was there at all.

“I didn’t mean to drop by,” Techno said eventually. “I didn’t know this place was yours. I can leave, if you—”

“No.” Philza shook his head. “Don’t leave. Truth be told, this reunion was inevitable. Or, I hoped it was.”

“How long have you been here?”

Philza considered. “How long has this kingdom been standing?”

“Phil, that’s—”

“I know. People like us aren’t meant to stay in one place for too long.” Philza sighed and turned back towards the horizon. He leaned his arms against the wrought-iron railings and looked out at the land beyond—the slope of the distant mountains, the kingdom that stretched on and on, unaware that their immortal king was all that stood between them and destruction. “I found a small town while I was traveling, made it something more. I told myself I would leave after a year, and then it became two years, three years, a decade. I did leave eventually, before they figured out why their town mayor never aged. But then I found out, the moment I left…” Philza’s expression turned cold. “They were annihilated. I came back and everything, everyone had been burnt to the ground. It was just ashes. Everything I built… There were survivors, of course, and they blamed their leader for leaving, of course—as they should. So I stayed. I built it back up again, from a small, decimated town to what you see today. As far as the people know, leadership has been passed on from one king to another who looks vaguely like him I’m sure the eldest of them have their rumors, but is it really so bad to be known?”

Technoblade didn’t realize, until Philza turned back to look at him, that he expected an answer to his question. But all Technoblade could say was, “Is this why you left me behind?”

“Techno—”

“I understand. You heard the place you loved was in trouble, so you came back, but I don’t—I just—why didn’t you take me?” Here it was, at last. Catharsis, or something close to it. “I would have hunted them down with you, Philza, the people who did that to your town. I would have given you your vengeance on a silver platter. I would have given you the world.”

Philza didn’t look guilty. He just looked tired. “I didn’t hunt them down, though.”

“What?”

“The people who burned down my town. I didn’t hunt them down, as much as I wanted to. They were long gone by the time I arrived, and at that moment, my people needed a leader, not a hunter. And I didn’t bring you because—”

“Because I don’t know when to be either.”

They stood there, letting the words settle in the silence that stretched tauter and tauter like a rope around Technoblade’s neck.

Deny it, he wanted to shout, tell me I’m wrong.

Philza did not.

“I don’t need to hear this from you,” Technoblade spat. A well of old hurt and anger, once dried up, began to fill anew. “Do your sons even know what you are? Who you are? The Angel of Death, domesticated. What a farce.”

 Philza stiffened. “You know not of which you speak.”

“I once saw you tear a man apart with your bare hands, and now you’re telling me about leadership? About kindness?”

“I said nothing of kindness. If I had completely renounced my ways, my kingdom would not be what it is today. Domesticated dogs still bite.”

Philza stepped towards him until they were eye-to-eye. Despite the accusations Techno hurled at him, despite their bloody history, Techno had never truly seen Philza angry. But he had a feeling that if he kept running down this road headfirst, he might find himself knowing the full extent of his old friend’s wrath. Philza’s eyes were hard as flint—one spark away from combustion.

Technoblade glanced down at the garden. Phil followed his gaze until they were both staring back at the two boys below, who’d ceased their playing to wonder at their father and the stranger.

They couldn’t have heard a thing of what Philza or Techno said, but Wilbur stood with his head cocked inquisitively to the side, as if he were turning over the words.

“Dad!” Tommy shouted. “Are you almost finished?”

“Almost!” Philza called back. “I’ll be right down, kids!”

Tommy elbowed Wilbur and said something that made the other boy throw his head back in laughter. Then the two of them took off, back to their games, back to their honeyed childhood. When Technoblade turned to Phil again, the king’s expression had turned considerably softer. Techno could live another thousand years and still never understand how easily Philza could hide his fury.

“I wasn’t trying to… settle down,” Philza said, quietly now, as if he was imploring a child to stop a tantrum. His eyes were still on his sons below. “I was content, for a while, to watch the kingdom grow. But these mortals and their short, fitful lives… they draw you in, Technoblade. I used to think they were moths drawn to flame, doomed to catch fire for the most inconsequential things. We’ve seen their wars, you and I. We’ve fought them. We both know the things they do to each other.” Philza took hold of the balcony railings as if it was the only thing keeping him from floating away. “But over the years I’ve also learned of the things they do for each other. Their lives will always be one year, one week, one day short, but it doesn’t seem to matter much to them. They live anyway. They love anyway. Forgive an old god for wanting a piece of that for himself.”

A late morning breeze passed through, carrying with it the scent of flowers and the shredded remains of Techno’s anguish. The fury was still there, and the feeling of a betrayal so grand it might never be bridged, but the exhaustion had begun to settle in. Techno was used to quick brawls and long hunts, but verbal altercation was not something he’d ever trained for—mostly because he had not cared to speak to anyone that mattered since… since forever, perhaps.
        </div>;
    </Router>
  )
}

export default App;
