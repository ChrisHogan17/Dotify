package edu.washington.hoganc17.dotify.manager

import com.google.gson.Gson
import edu.washington.hoganc17.dotify.model.Song
import edu.washington.hoganc17.dotify.model.SongPackage

class ApiManager {
    private val testJSONString: String = """
        {
          "title": "Dotify",
          "numOfSongs": 47,
          "songs": [
            {
              "id": "1588825540885InTheEnd_LinkinPark",
              "title": "In The End",
              "artist": "Linkin Park",
              "durationMillis": 193790,
              "smallImageURL": "https://picsum.photos/seed/InTheEnd/50",
              "largeImageURL": "https://picsum.photos/seed/InTheEnd/256"
            },
            {
              "id": "1588825540953MaskDefinitelyOn_Future",
              "title": "Mask Definitely On",
              "artist": "Future",
              "durationMillis": 92949,
              "smallImageURL": "https://picsum.photos/seed/MaskDefinitelyOn/50",
              "largeImageURL": "https://picsum.photos/seed/MaskDefinitelyOn/256"
            },
            {
              "id": "1588825540953Can\u0027tTouchThis_MCHammer",
              "title": "Can\u0027t Touch This",
              "artist": "MC Hammer",
              "durationMillis": 108709,
              "smallImageURL": "https://picsum.photos/seed/Can%27tTouchThis/50",
              "largeImageURL": "https://picsum.photos/seed/Can%27tTouchThis/256"
            },
            {
              "id": "1588825540954StayWithMe_SamSmith",
              "title": "Stay With Me",
              "artist": "Sam Smith",
              "durationMillis": 165164,
              "smallImageURL": "https://picsum.photos/seed/StayWithMe/50",
              "largeImageURL": "https://picsum.photos/seed/StayWithMe/256"
            },
            {
              "id": "1588825540954Yummy_JustinBieber",
              "title": "Yummy",
              "artist": "Justin Bieber",
              "durationMillis": 162106,
              "smallImageURL": "https://picsum.photos/seed/Yummy/50",
              "largeImageURL": "https://picsum.photos/seed/Yummy/256"
            },
            {
              "id": "1588825540954Ddu-duDdu-du_BlackPink",
              "title": "Ddu-du Ddu-du",
              "artist": "Black Pink",
              "durationMillis": 241936,
              "smallImageURL": "https://picsum.photos/seed/Ddu-duDdu-du/50",
              "largeImageURL": "https://picsum.photos/seed/Ddu-duDdu-du/256"
            },
            {
              "id": "1588825540954LookWhatYouMadeMeDo_TaylorSwift",
              "title": "Look What You Made Me Do",
              "artist": "Taylor Swift",
              "durationMillis": 132213,
              "smallImageURL": "https://picsum.photos/seed/LookWhatYouMadeMeDo/50",
              "largeImageURL": "https://picsum.photos/seed/LookWhatYouMadeMeDo/256"
            },
            {
              "id": "1588825540954OldTownRoad_LilNasX",
              "title": "Old Town Road",
              "artist": "Lil Nas X",
              "durationMillis": 143307,
              "smallImageURL": "https://picsum.photos/seed/OldTownRoad/50",
              "largeImageURL": "https://picsum.photos/seed/OldTownRoad/256"
            },
            {
              "id": "1588825540955Memories_Maroon5",
              "title": "Memories",
              "artist": "Maroon 5",
              "durationMillis": 62546,
              "smallImageURL": "https://picsum.photos/seed/Memories/50",
              "largeImageURL": "https://picsum.photos/seed/Memories/256"
            },
            {
              "id": "1588825540955Don\u0027tStartNow_DuaLipa",
              "title": "Don\u0027t Start Now",
              "artist": "Dua Lipa",
              "durationMillis": 103572,
              "smallImageURL": "https://picsum.photos/seed/Don%27tStartNow/50",
              "largeImageURL": "https://picsum.photos/seed/Don%27tStartNow/256"
            },
            {
              "id": "1588825540955BeforeYouGo_LewisCapaldi",
              "title": "Before You Go",
              "artist": "Lewis Capaldi",
              "durationMillis": 142439,
              "smallImageURL": "https://picsum.photos/seed/BeforeYouGo/50",
              "largeImageURL": "https://picsum.photos/seed/BeforeYouGo/256"
            },
            {
              "id": "1588825540955O.G._TroyBoi",
              "title": "O.G.",
              "artist": "TroyBoi",
              "durationMillis": 86572,
              "smallImageURL": "https://picsum.photos/seed/O.G./50",
              "largeImageURL": "https://picsum.photos/seed/O.G./256"
            },
            {
              "id": "1588825540955SomeoneYouLoved_LewisCapaldi",
              "title": "Someone You Loved",
              "artist": "Lewis Capaldi",
              "durationMillis": 207078,
              "smallImageURL": "https://picsum.photos/seed/SomeoneYouLoved/50",
              "largeImageURL": "https://picsum.photos/seed/SomeoneYouLoved/256"
            },
            {
              "id": "1588825540955LuckyYou_Eminemfeat.JoynerLucas",
              "title": "Lucky You",
              "artist": "Eminem feat. Joyner Lucas",
              "durationMillis": 239725,
              "smallImageURL": "https://picsum.photos/seed/LuckyYou/50",
              "largeImageURL": "https://picsum.photos/seed/LuckyYou/256"
            },
            {
              "id": "1588825540956CrazyInLove_Beyoncefeat.Jay-Z",
              "title": "Crazy In Love",
              "artist": "Beyonce feat. Jay-Z",
              "durationMillis": 94759,
              "smallImageURL": "https://picsum.photos/seed/CrazyInLove/50",
              "largeImageURL": "https://picsum.photos/seed/CrazyInLove/256"
            },
            {
              "id": "1588825540956InTheNameOfLove_MartinGarrixfeat.BebeRexha",
              "title": "In The Name Of Love",
              "artist": "Martin Garrix feat. Bebe Rexha",
              "durationMillis": 76822,
              "smallImageURL": "https://picsum.photos/seed/InTheNameOfLove/50",
              "largeImageURL": "https://picsum.photos/seed/InTheNameOfLove/256"
            },
            {
              "id": "1588825540956RewriteTheStars_ZacEfron\u0026Zendaya",
              "title": "Rewrite The Stars",
              "artist": "Zac Efron \u0026 Zendaya",
              "durationMillis": 181936,
              "smallImageURL": "https://picsum.photos/seed/RewriteTheStars/50",
              "largeImageURL": "https://picsum.photos/seed/RewriteTheStars/256"
            },
            {
              "id": "1588825540956I\u0027mOnABoat_TheLonelyIslandfeat.T-Pain",
              "title": "I\u0027m On A Boat",
              "artist": "The Lonely Island feat. T-Pain",
              "durationMillis": 172869,
              "smallImageURL": "https://picsum.photos/seed/I%27mOnABoat/50",
              "largeImageURL": "https://picsum.photos/seed/I%27mOnABoat/256"
            },
            {
              "id": "1588825540957Despacito(Remix)_LuisFonsifeat.DaddyYankee\u0026JustinBieber",
              "title": "Despacito (Remix)",
              "artist": "Luis Fonsi feat. Daddy Yankee \u0026 Justin Bieber",
              "durationMillis": 253279,
              "smallImageURL": "https://picsum.photos/seed/Despacito%28Remix%29/50",
              "largeImageURL": "https://picsum.photos/seed/Despacito%28Remix%29/256"
            },
            {
              "id": "1588825540957LikeAG6_FarEastMovementfeat.TheCataracs\u0026DEV",
              "title": "Like A G6",
              "artist": "Far East Movement feat. The Cataracs \u0026 DEV",
              "durationMillis": 134168,
              "smallImageURL": "https://picsum.photos/seed/LikeAG6/50",
              "largeImageURL": "https://picsum.photos/seed/LikeAG6/256"
            },
            {
              "id": "1588825540957LaLaLand(feat.YG)_BryceVine,YG",
              "title": "La La Land (feat. YG)",
              "artist": "Bryce Vine, YG",
              "durationMillis": 155607,
              "smallImageURL": "https://picsum.photos/seed/LaLaLand%28feat.YG%29/50",
              "largeImageURL": "https://picsum.photos/seed/LaLaLand%28feat.YG%29/256"
            },
            {
              "id": "1588825540957AWayToSayGoodbye_SevenLions,Sombear",
              "title": "A Way To Say Goodbye",
              "artist": "Seven Lions, Sombear",
              "durationMillis": 76122,
              "smallImageURL": "https://picsum.photos/seed/AWayToSayGoodbye/50",
              "largeImageURL": "https://picsum.photos/seed/AWayToSayGoodbye/256"
            },
            {
              "id": "1588825540957OneCallAway_CharliePuth",
              "title": "One Call Away",
              "artist": "Charlie Puth",
              "durationMillis": 157807,
              "smallImageURL": "https://picsum.photos/seed/OneCallAway/50",
              "largeImageURL": "https://picsum.photos/seed/OneCallAway/256"
            },
            {
              "id": "1588825540958Time_PinkFloyd",
              "title": "Time",
              "artist": "Pink Floyd",
              "durationMillis": 244612,
              "smallImageURL": "https://picsum.photos/seed/Time/50",
              "largeImageURL": "https://picsum.photos/seed/Time/256"
            },
            {
              "id": "1588825540958Everglow_Coldplay",
              "title": "Everglow",
              "artist": "Coldplay",
              "durationMillis": 164096,
              "smallImageURL": "https://picsum.photos/seed/Everglow/50",
              "largeImageURL": "https://picsum.photos/seed/Everglow/256"
            },
            {
              "id": "1588825540958SelfControl_FrankOcean",
              "title": "Self Control",
              "artist": "Frank Ocean",
              "durationMillis": 271194,
              "smallImageURL": "https://picsum.photos/seed/SelfControl/50",
              "largeImageURL": "https://picsum.photos/seed/SelfControl/256"
            },
            {
              "id": "1588825540958Daisuke_ElHuervo",
              "title": "Daisuke",
              "artist": "El Huervo",
              "durationMillis": 93629,
              "smallImageURL": "https://picsum.photos/seed/Daisuke/50",
              "largeImageURL": "https://picsum.photos/seed/Daisuke/256"
            },
            {
              "id": "1588825540958Luck_AmericanAuthors",
              "title": "Luck",
              "artist": "American Authors",
              "durationMillis": 167543,
              "smallImageURL": "https://picsum.photos/seed/Luck/50",
              "largeImageURL": "https://picsum.photos/seed/Luck/256"
            },
            {
              "id": "1588825540958DarkSouls3_YukaKitamura",
              "title": "Dark Souls 3",
              "artist": "Yuka Kitamura",
              "durationMillis": 262980,
              "smallImageURL": "https://picsum.photos/seed/DarkSouls3/50",
              "largeImageURL": "https://picsum.photos/seed/DarkSouls3/256"
            },
            {
              "id": "1588825540958SleepOnTheFloor_TheLumineers",
              "title": "Sleep On The Floor",
              "artist": "The Lumineers",
              "durationMillis": 137408,
              "smallImageURL": "https://picsum.photos/seed/SleepOnTheFloor/50",
              "largeImageURL": "https://picsum.photos/seed/SleepOnTheFloor/256"
            },
            {
              "id": "1588825540958NancyMulligan_EdSheeran",
              "title": "Nancy Mulligan",
              "artist": "Ed Sheeran",
              "durationMillis": 281494,
              "smallImageURL": "https://picsum.photos/seed/NancyMulligan/50",
              "largeImageURL": "https://picsum.photos/seed/NancyMulligan/256"
            },
            {
              "id": "1588825540959Takeaway_TheChainsmokers,ILLENIUM,LennonStella",
              "title": "Takeaway",
              "artist": "The Chainsmokers, ILLENIUM, Lennon Stella",
              "durationMillis": 252391,
              "smallImageURL": "https://picsum.photos/seed/Takeaway/50",
              "largeImageURL": "https://picsum.photos/seed/Takeaway/256"
            },
            {
              "id": "1588825540959Astronomia_Vicetone,TonyIgy",
              "title": "Astronomia",
              "artist": "Vicetone, Tony Igy",
              "durationMillis": 261539,
              "smallImageURL": "https://picsum.photos/seed/Astronomia/50",
              "largeImageURL": "https://picsum.photos/seed/Astronomia/256"
            },
            {
              "id": "1588825540959RushOverMe_SevenLions,ILLENIUM,SaidtheSky,HALIENE",
              "title": "Rush Over Me",
              "artist": "Seven Lions, ILLENIUM, Said the Sky, HALIENE",
              "durationMillis": 223101,
              "smallImageURL": "https://picsum.photos/seed/RushOverMe/50",
              "largeImageURL": "https://picsum.photos/seed/RushOverMe/256"
            },
            {
              "id": "1588825540959GotTheGuap(feat.YoungThug)_LilUziVert,YoungThug",
              "title": "Got The Guap (feat. Young Thug)",
              "artist": "Lil Uzi Vert, Young Thug",
              "durationMillis": 193754,
              "smallImageURL": "https://picsum.photos/seed/GotTheGuap%28feat.YoungThug%29/50",
              "largeImageURL": "https://picsum.photos/seed/GotTheGuap%28feat.YoungThug%29/256"
            },
            {
              "id": "1588825540959SadSongs(\u0026SaidTheSkywithAnnikaWells)_ILLENIUM,SaidtheSky,AnnikaWells",
              "title": "Sad Songs (\u0026 Said The Sky with Annika Wells)",
              "artist": "ILLENIUM, Said the Sky, Annika Wells",
              "durationMillis": 176786,
              "smallImageURL": "https://picsum.photos/seed/SadSongs%28%26SaidTheSkywithAnnikaWells%29/50",
              "largeImageURL": "https://picsum.photos/seed/SadSongs%28%26SaidTheSkywithAnnikaWells%29/256"
            },
            {
              "id": "1588825540960SnowAngels_Anamanaguchi",
              "title": "Snow Angels",
              "artist": "Anamanaguchi",
              "durationMillis": 85215,
              "smallImageURL": "https://picsum.photos/seed/SnowAngels/50",
              "largeImageURL": "https://picsum.photos/seed/SnowAngels/256"
            },
            {
              "id": "1588825540960Nights_FrankOcean",
              "title": "Nights",
              "artist": "Frank Ocean",
              "durationMillis": 248788,
              "smallImageURL": "https://picsum.photos/seed/Nights/50",
              "largeImageURL": "https://picsum.photos/seed/Nights/256"
            },
            {
              "id": "1588825540960GIANTS_TrueDamage,BeckyG,KekePalmer,SOYEONof(G)I-DLE,Duckwrth,Thutmose,LeagueofLegends",
              "title": "GIANTS",
              "artist": "True Damage, Becky G, Keke Palmer, SOYEON of (G)I-DLE, Duckwrth, Thutmose, League of Legends",
              "durationMillis": 284061,
              "smallImageURL": "https://picsum.photos/seed/GIANTS/50",
              "largeImageURL": "https://picsum.photos/seed/GIANTS/256"
            },
            {
              "id": "1588825540960NobodyKnows_Russ",
              "title": "Nobody Knows",
              "artist": "Russ",
              "durationMillis": 73127,
              "smallImageURL": "https://picsum.photos/seed/NobodyKnows/50",
              "largeImageURL": "https://picsum.photos/seed/NobodyKnows/256"
            },
            {
              "id": "1588825540960ThoughtContagion_Muse",
              "title": "Thought Contagion",
              "artist": "Muse",
              "durationMillis": 154872,
              "smallImageURL": "https://picsum.photos/seed/ThoughtContagion/50",
              "largeImageURL": "https://picsum.photos/seed/ThoughtContagion/256"
            },
            {
              "id": "1588825540960Lemon_KenshiYonezu",
              "title": "Lemon",
              "artist": "Kenshi Yonezu",
              "durationMillis": 258724,
              "smallImageURL": "https://picsum.photos/seed/Lemon/50",
              "largeImageURL": "https://picsum.photos/seed/Lemon/256"
            },
            {
              "id": "1588825540960Warriors_LeagueofLegends,2WEI,EddaHayes",
              "title": "Warriors",
              "artist": "League of Legends, 2WEI, Edda Hayes",
              "durationMillis": 276468,
              "smallImageURL": "https://picsum.photos/seed/Warriors/50",
              "largeImageURL": "https://picsum.photos/seed/Warriors/256"
            },
            {
              "id": "1588825540960It\u0027sSomethingtoBeAlive_DREAMERS",
              "title": "It\u0027s Something to Be Alive",
              "artist": "DREAMERS",
              "durationMillis": 144653,
              "smallImageURL": "https://picsum.photos/seed/It%27sSomethingtoBeAlive/50",
              "largeImageURL": "https://picsum.photos/seed/It%27sSomethingtoBeAlive/256"
            },
            {
              "id": "1588825540960明月天涯_五音JW",
              "title": "明月天涯",
              "artist": "五音JW",
              "durationMillis": 91203,
              "smallImageURL": "https://picsum.photos/seed/%E6%98%8E%E6%9C%88%E5%A4%A9%E6%B6%AF/50",
              "largeImageURL": "https://picsum.photos/seed/%E6%98%8E%E6%9C%88%E5%A4%A9%E6%B6%AF/256"
            },
            {
              "id": "1588825540961Cadillactica_BIGK.R.I.T.",
              "title": "Cadillactica",
              "artist": "BIG K.R.I.T.",
              "durationMillis": 276554,
              "smallImageURL": "https://picsum.photos/seed/Cadillactica/50",
              "largeImageURL": "https://picsum.photos/seed/Cadillactica/256"
            },
            {
              "id": "1588825540961God\u0027sPlan_Drake",
              "title": "God\u0027s Plan",
              "artist": "Drake",
              "durationMillis": 274652,
              "smallImageURL": "https://picsum.photos/seed/God%27sPlan/50",
              "largeImageURL": "https://picsum.photos/seed/God%27sPlan/256"
            }
          ]
        }
    """.trimIndent()
    private val testSongString: String = """
        {
          "id": "1588825540885InTheEnd_LinkinPark",
          "title": "In The End",
          "artist": "Linkin Park",
          "durationMillis": 193790,
          "smallImageURL": "https://picsum.photos/seed/InTheEnd/50",
          "largeImageURL": "https://picsum.photos/seed/InTheEnd/256"
        }
    """.trimIndent()


    fun fetchSongs(): List<Song> {
        val gson = Gson()
        val retrievedSongs: SongPackage = gson.fromJson(testJSONString, SongPackage::class.java)
        return retrievedSongs.songs
    }
}