package com.tuyp.mymovie.utils

import com.tuyp.mymovie.data.local.entity.*
import com.tuyp.mymovie.data.remote.model.resource.*

object DataDummy {
    fun generateMovie() : List<MovieResource>{
        val movie = ArrayList<MovieResource>()
        movie.add(MovieResource(id = 1,"The Tomorrow War",
            ConstVal.IMG_URL + "/yizL4cEKsVvl17Wc1mGEIrQtM2F.jpg",
            "The world is stunned when a group of time travelers arrive from the year 2051 to deliver an urgent message: Thirty years in the future, mankind is losing a global war against a deadly alien species. The only hope for survival is for soldiers and civilians from the present to be transported to the future and join the fight. Among those recruited is high school teacher and family man Dan Forester. Determined to save the world for his young daughter, Dan teams up with a brilliant scientist and his estranged father in a desperate quest to rewrite the fate of the planet.",
            2878.154,"en"))
        movie.add(MovieResource(id = 2,
            "The Suicide Squad",
            "/iXbWpCkIauBMStSTUT9v4GXvdgH.jpg",
            "Supervillains Harley Quinn, Bloodsport, Peacemaker and a collection of nutty cons at Belle Reve prison join the super-secret, super-shady Task Force X as they are dropped off at the remote, enemy-infused island of Corto Maltese.",
            3184.405,"en"))
        movie.add(MovieResource(id = 3,
            "The Forever Purge",
            "/lB068qa6bQ0QKYKyC2xnYGvYjl7.jpg",
            "All the rules are broken as a sect of lawless marauders decides that the annual Purge does not stop at daybreak and instead should never end as they chase a group of immigrants who they want to punish because of their harsh historical past.",
            687.349,"en"))
        movie.add(MovieResource(id = 4,"Shang-Chi and the Legend of the Ten Rings",
            "/nDLylQOoIazGyYuWhk21Yww5FCb.jpg",
            "Shang-Chi must confront the past he thought he left behind when he is drawn into the web of the mysterious Ten Rings organization.",
            2878.154,"en"))
        movie.add(MovieResource(id = 5,"Jungle Cruise",
            "/9dKCd55IuTT5QRs989m9Qlb7d2B.jpg",
            "Dr. Lily Houghton enlists the aid of wisecracking skipper Frank Wolff to take her down the Amazon in his dilapidated boat. Together, they search for an ancient tree that holds the power to heal – a discovery that will change the future of medicine.",
            2189.931,"en"))
        movie.add(MovieResource(id = 6,
            "PAW Patrol: The Movie",
            "/ic0intvXZSfBlYPIvWXpU1ivUCO.jpg",
            "Ryder and the pups are called to Adventure City to stop Mayor Humdinger from turning the bustling metropolis into a state of chaos.",
            2878.154,"en"))
        movie.add(MovieResource(id = 7,"Space Jam: A New Legacy",
            "/5bFK5d3mVTAvBCXi5NPWH0tYjKl.jpg",
            "When LeBron and his young son Dom are trapped in a digital space by a rogue A.I., LeBron must get them home safe by leading Bugs, Lola Bunny and the whole gang of notoriously undisciplined Looney Tunes to victory over the A.I.'s digitized champions on the court. It's Tunes versus Goons in the highest-stakes challenge of his life.",
            1500.991,"en"))
        movie.add(MovieResource(id = 8,"Sinaliento",
            "/oxNoVgbu2v9ETL93Kri1pw8osYf.jpg",
            "",
            1475.545,"en"))
        movie.add(MovieResource(id = 9,"Free Guy",
            "/hEqw9swA8gFJuNjgWYEypwZfkZg.jpg",
            " bank teller called Guy realizes he is a background character in an open world video game called Free City that will soon go offline",
            2878.154,"en"))
        movie.add(MovieResource(id = 10,"Un rescate de huevitos",
            "/wrlQnKHLCBheXMNWotyr5cVDqNM.jpg",
            "A rooster and his fowl partner embark on a dangerous trip to the Congo to recover their stolen eggs from a group of Russian goons.",
            2878.154,"en"))

        return movie
    }



    fun generateDetailMovie(dataMovie : MovieResource,genres : List<Genre>): MovieDetail{
        val detailMovie : MovieDetail
        detailMovie = MovieDetail(dataMovie.id,
            dataMovie.title,
            dataMovie.backdropPath,
            dataMovie.overview,
            dataMovie.popularity,
            dataMovie.originalLanguage,
                     genres)
        return detailMovie
    }

    fun generateDetailMovieLocal(dataMovie : MovieEntity,genres : List<GenreMovieEntity>): MovieWithGenreEntity{
        val detailMovie : MovieEntity
        detailMovie = MovieEntity(dataMovie.movieId,
            dataMovie.title,
            dataMovie.backdropPath,
            dataMovie.overview,
            dataMovie.popularity,
            dataMovie.originalLanguage)

        return MovieWithGenreEntity(detailMovie,genres)
    }

    fun generateDetailShowLocal(dataShow : TvShowEntity,genres: List<GenreTvEntity>): TvWithGenreEntity{
        val detailTv : TvShowEntity
        detailTv = TvShowEntity(dataShow.showId,
            dataShow.title,
            dataShow.backdropPath,
            dataShow.overview,
            dataShow.popularity,
            dataShow.originalLanguage)
        return TvWithGenreEntity(detailTv,genres)
    }

    fun generateDetailShow(dataShow : TvShowResource,genres: List<Genre>) : TvShowDetail{
        val detailMovie : TvShowDetail
        detailMovie = TvShowDetail(dataShow.id,
            dataShow.name,
            dataShow.backdropPath,
            dataShow.overview,
            dataShow.popularity,
            dataShow.originalLanguage,
            genres)
        return detailMovie
    }

    fun generateMovieAPIDummy() : List<MovieResource>{
        val movie = ArrayList<MovieResource>()
        movie.add(MovieResource(id = 1,"The Tomorrow War",
            ConstVal.IMG_URL + "/yizL4cEKsVvl17Wc1mGEIrQtM2F.jpg",
            "The world is stunned when a group of time travelers arrive from the year 2051 to deliver an urgent message: Thirty years in the future, mankind is losing a global war against a deadly alien species. The only hope for survival is for soldiers and civilians from the present to be transported to the future and join the fight. Among those recruited is high school teacher and family man Dan Forester. Determined to save the world for his young daughter, Dan teams up with a brilliant scientist and his estranged father in a desperate quest to rewrite the fate of the planet.",
            2878.154,"en"))
        movie.add(MovieResource(id = 2,
            "The Suicide Squad",
            "/iXbWpCkIauBMStSTUT9v4GXvdgH.jpg",
            "Supervillains Harley Quinn, Bloodsport, Peacemaker and a collection of nutty cons at Belle Reve prison join the super-secret, super-shady Task Force X as they are dropped off at the remote, enemy-infused island of Corto Maltese.",
            3184.405,"en"))
        movie.add(MovieResource(id = 3,
            "The Forever Purge",
            "/lB068qa6bQ0QKYKyC2xnYGvYjl7.jpg",
            "All the rules are broken as a sect of lawless marauders decides that the annual Purge does not stop at daybreak and instead should never end as they chase a group of immigrants who they want to punish because of their harsh historical past.",
            687.349,"en"))
        movie.add(MovieResource(id = 4,"Shang-Chi and the Legend of the Ten Rings",
            "/nDLylQOoIazGyYuWhk21Yww5FCb.jpg",
            "Shang-Chi must confront the past he thought he left behind when he is drawn into the web of the mysterious Ten Rings organization.",
            2878.154,"en"))
        movie.add(MovieResource(id = 5,"Jungle Cruise",
            "/9dKCd55IuTT5QRs989m9Qlb7d2B.jpg",
            "Dr. Lily Houghton enlists the aid of wisecracking skipper Frank Wolff to take her down the Amazon in his dilapidated boat. Together, they search for an ancient tree that holds the power to heal – a discovery that will change the future of medicine.",
            2189.931,"en"))
        movie.add(MovieResource(id = 6,
            "PAW Patrol: The Movie",
            "/ic0intvXZSfBlYPIvWXpU1ivUCO.jpg",
            "Ryder and the pups are called to Adventure City to stop Mayor Humdinger from turning the bustling metropolis into a state of chaos.",
            2878.154,"en"))
        movie.add(MovieResource(id = 7,"Space Jam: A New Legacy",
            "/5bFK5d3mVTAvBCXi5NPWH0tYjKl.jpg",
            "When LeBron and his young son Dom are trapped in a digital space by a rogue A.I., LeBron must get them home safe by leading Bugs, Lola Bunny and the whole gang of notoriously undisciplined Looney Tunes to victory over the A.I.'s digitized champions on the court. It's Tunes versus Goons in the highest-stakes challenge of his life.",
            1500.991,"en"))
        movie.add(MovieResource(id = 8,"Sinaliento",
            "/oxNoVgbu2v9ETL93Kri1pw8osYf.jpg",
            "",
            1475.545,"en"))
        movie.add(MovieResource(id = 9,"Free Guy",
            "/hEqw9swA8gFJuNjgWYEypwZfkZg.jpg",
            " bank teller called Guy realizes he is a background character in an open world video game called Free City that will soon go offline",
            2878.154,"en"))
        movie.add(MovieResource(id = 10,"Un rescate de huevitos",
            "/wrlQnKHLCBheXMNWotyr5cVDqNM.jpg",
            "A rooster and his fowl partner embark on a dangerous trip to the Congo to recover their stolen eggs from a group of Russian goons.",
            2878.154,"en"))

        return movie
    }

    fun generateMovieLocalDummy() : List<MovieEntity>{
        val movie = ArrayList<MovieEntity>()

        movie.add(MovieEntity("1","The Tomorrow War",
            ConstVal.IMG_URL + "/yizL4cEKsVvl17Wc1mGEIrQtM2F.jpg",
            "The world is stunned when a group of time travelers arrive from the year 2051 to deliver an urgent message: Thirty years in the future, mankind is losing a global war against a deadly alien species. The only hope for survival is for soldiers and civilians from the present to be transported to the future and join the fight. Among those recruited is high school teacher and family man Dan Forester. Determined to save the world for his young daughter, Dan teams up with a brilliant scientist and his estranged father in a desperate quest to rewrite the fate of the planet.",
            "2878.154","en"))
        movie.add(MovieEntity("2",
            "The Suicide Squad",
            "/iXbWpCkIauBMStSTUT9v4GXvdgH.jpg",
            "Supervillains Harley Quinn, Bloodsport, Peacemaker and a collection of nutty cons at Belle Reve prison join the super-secret, super-shady Task Force X as they are dropped off at the remote, enemy-infused island of Corto Maltese.",
            "3184.405","en"))
        movie.add(MovieEntity("3",
            "The Forever Purge",
            "/lB068qa6bQ0QKYKyC2xnYGvYjl7.jpg",
            "All the rules are broken as a sect of lawless marauders decides that the annual Purge does not stop at daybreak and instead should never end as they chase a group of immigrants who they want to punish because of their harsh historical past.",
            "687.349","en"))
        movie.add(MovieEntity("4","Shang-Chi and the Legend of the Ten Rings",
            "/nDLylQOoIazGyYuWhk21Yww5FCb.jpg",
            "Shang-Chi must confront the past he thought he left behind when he is drawn into the web of the mysterious Ten Rings organization.",
            "2878.154","en"))
        movie.add(MovieEntity("5","Jungle Cruise",
            "/9dKCd55IuTT5QRs989m9Qlb7d2B.jpg",
            "Dr. Lily Houghton enlists the aid of wisecracking skipper Frank Wolff to take her down the Amazon in his dilapidated boat. Together, they search for an ancient tree that holds the power to heal – a discovery that will change the future of medicine.",
            "2189.931","en"))
        movie.add(MovieEntity("6",
            "PAW Patrol: The Movie",
            "/ic0intvXZSfBlYPIvWXpU1ivUCO.jpg",
            "Ryder and the pups are called to Adventure City to stop Mayor Humdinger from turning the bustling metropolis into a state of chaos.",
            "2878.154","en"))
        movie.add(MovieEntity("7","Space Jam: A New Legacy",
            "/5bFK5d3mVTAvBCXi5NPWH0tYjKl.jpg",
            "When LeBron and his young son Dom are trapped in a digital space by a rogue A.I., LeBron must get them home safe by leading Bugs, Lola Bunny and the whole gang of notoriously undisciplined Looney Tunes to victory over the A.I.'s digitized champions on the court. It's Tunes versus Goons in the highest-stakes challenge of his life.",
            "1500.991","en"))
        movie.add(MovieEntity("8","Sinaliento",
            "/oxNoVgbu2v9ETL93Kri1pw8osYf.jpg",
            "",
            "1475.545","en"))
        movie.add(MovieEntity("9","Free Guy",
            "/hEqw9swA8gFJuNjgWYEypwZfkZg.jpg",
            " bank teller called Guy realizes he is a background character in an open world video game called Free City that will soon go offline",
            "2878.154","en"))
        movie.add(MovieEntity("10","Un rescate de huevitos",
            "/wrlQnKHLCBheXMNWotyr5cVDqNM.jpg",
            "A rooster and his fowl partner embark on a dangerous trip to the Congo to recover their stolen eggs from a group of Russian goons.",
            "2878.154","en"))

        return movie
    }


    fun generateGenre() : List<Genre>{
        val genre = ArrayList<Genre>()
        genre.add(Genre(1,"Action"))
        genre.add(Genre(1,"Science Fiction"))
        genre.add(Genre(1,"Adventure"))
        genre.add(Genre(2,"Action"))
        genre.add(Genre(2,"Adventure"))
        genre.add(Genre(2,"Fantasy"))
        genre.add(Genre(2,"Comedy"))
        genre.add(Genre(3,"Horror"))
        genre.add(Genre(3,"Action"))
        genre.add(Genre(3,"Thriller"))
        genre.add(Genre(4,"Action"))
        genre.add(Genre(4,"Adventure"))
        genre.add(Genre(4,"Fantasy"))
        genre.add(Genre(4,"Comedy"))
        genre.add(Genre(5,"Family"))
        genre.add(Genre(5,"Adventure"))
        genre.add(Genre(5,"Animation"))
        genre.add(Genre(5,"Comedy"))
        genre.add(Genre(6,"Action"))
        genre.add(Genre(6,"Adventure"))
        genre.add(Genre(6,"Fantasy"))
        genre.add(Genre(7,"Animation"))
        genre.add(Genre(7,"Comedy"))
        genre.add(Genre(7,"Family"))
        genre.add(Genre(7,"Science Fiction"))
        genre.add(Genre(8,"Crime"))
        genre.add(Genre(8,"Drama"))
        genre.add(Genre(8,"Thriller"))
        genre.add(Genre(9,"Comedy"))
        genre.add(Genre(9,"Action"))
        genre.add(Genre(9,"Adventure"))
        genre.add(Genre(9,"Science Fiction"))
        genre.add(Genre(10,"Animation"))
        genre.add(Genre(10,"Comedy"))

        return genre
    }
    fun generateShowAPI() : List<TvShowResource>{
        val tvShow = ArrayList<TvShowResource>()

        tvShow.add(TvShowResource(1,
            "The Bond",
            "/zN8vBX1jDRxIDqFQ2ARcodTHhdt.jpg",
            "",
            1504.291,"zh"))
        tvShow.add(TvShowResource(2,
            "Ilha Record",
            "/zN8vBX1jDRxIDqFQ2ARcodTHhdt.jpg",
            "Ilha Record is a Brazilian reality television competition format originally created and aired by RecordTV. The series is hosted by Sabrina Sato and premiered on Monday",
            1449.274,"pt"))
        tvShow.add(TvShowResource(3,
            "The Walking Dead",
            "/w21lgYIi9GeUH5dO8l3B9ARZbCB.jpg",
            "Sheriff's deputy Rick Grimes awakens from a coma to find a post-apocalyptic world dominated by flesh-eating zombies. He sets out to find his family and encounters many other survivors along the way.",
            889.031,"en"))
        tvShow.add(TvShowResource(4,
            "Rick and Morty",
            "/8kOWDBK6XlPUzckuHDo3wwVRFwt.jpg",
            "Rick is a mentally-unbalanced but scientifically gifted old man who has recently reconnected with his family. He spends most of his time involving his young grandson Morty in dangerous, outlandish adventures throughout space and alternate universes. Compounded with Morty's already unstable family life, these events cause Morty much distress at home and school.",
            797.194,"en"))
        tvShow.add(TvShowResource(5,
            "Anupamaa",
            "/9R4Qtm01pNh0CzdoIyvHKefe5RL.jpg",
            "Anupama had to sacrifice a lot to become an ideal wife, daughter-in-law, and mother. After a bitter realisation, she sets out to live on her own terms.",
            715.324,"hi"))
        tvShow.add(TvShowResource(6,
            "Tokyo Revengers",
            "/1VZ8gN6tPoAd7EdfCzVPmtDczhD.jpg\"",
            "Takemichi Hanagaki is a freelancer that’s reached the absolute pits of despair in his life. He finds out that the only girlfriend he ever had, in middle school, Hinata Tachibana, had been killed by the ruthless Tokyo Manji Gang. The day after hearing about her death, he’s standing on the station platform and ends up being pushed over onto the tracks by a herd of people. He closes his eyes thinking he’s about to die, but when he opens his eyes back up, he somehow had gone back in time 12 years. Now that he’s back living the best days of his life, Takemichi decides to get revenge on his life.",
            402.935,"jp"))
        tvShow.add(TvShowResource(7,
            "O Amor Acontece",
            "/aqvOIR4CzCQETw9pAuyXZYKd2Ni.jpg",
            "Originally from the Netherlands, Let Love Rule now has a Portuguese version. Each week, four new stories are shown, lived by different protagonists that we meet every Sunday. At the end of four days, a meeting dictates the participants' continuation as a couple - outside the experience - or their separation. In a ceremony, led by Maria Cerqueira Gomes and Pedro Teixeira, the couples evaluate their experience. Mafalda de Castro presents the diaries of this format, which is premised on helping singles find love.",
            329.89,"pt"))
        tvShow.add(TvShowResource(8,
            "Miraculous: Tales of Ladybug & Cat Noir",
            "/9RqliZcoDEjSEISeA0LY9meAiNv.jpg",
            "Normal high school kids by day, protectors of Paris by night! Miraculous follows the heroic adventures of Marinette and Adrien as they transform into Ladybug and Cat Noir and set out to capture akumas, creatures responsible for turning the people of Paris into villains. But neither hero knows the other’s true identity – or that they’re classmates!",
            278.659,"fr"))
        tvShow.add(TvShowResource(9,
            "Yeh Rishta Kya Kehlata Hai",
            "/jJpyrPNvPTNK5Y3qdYWZ5BDeg0b.jpg\"",
            "Naitik and Akshara Singhania live in a Marwari joint family in Udaipur. As a young married couple, they journey through adjustment issues, later as parents of young children, and they learn to love each other and their respective extended families while becoming mature members of their household.",
            238.768,"hi"))
        tvShow.add(TvShowResource(
            10,
            "/xBYfKcbDY0NktGn9f7QW8qaV96g.jpg",
            "Fena: Pirate Princess",
            "Fena Houtman remembers little about her childhood. Orphaned and raised as a servant in a brothel, her life changes when she escapes to an island of pirates where she discovers the truth behind her family. With Fena being the only one able to unlock her family’s secrets, and a formidable crew of female pirates on her tail, she must take her place as captain of her Samurai crew for a high seas adventure!",
            205.135,"ja"
        ))
        return tvShow
    }

    fun generateShowLocal() : List<TvShowEntity>{
        val tvShow = ArrayList<TvShowEntity>()

        tvShow.add(TvShowEntity("1",
            "The Bond",
            "/zN8vBX1jDRxIDqFQ2ARcodTHhdt.jpg",
            "",
            "1504.291","zh"))
        tvShow.add(TvShowEntity("2",
            "Ilha Record",
            "/zN8vBX1jDRxIDqFQ2ARcodTHhdt.jpg",
            "Ilha Record is a Brazilian reality television competition format originally created and aired by RecordTV. The series is hosted by Sabrina Sato and premiered on Monday",
            "1449.274","pt"))
        tvShow.add(TvShowEntity("3",
            "The Walking Dead",
            "/w21lgYIi9GeUH5dO8l3B9ARZbCB.jpg",
            "Sheriff's deputy Rick Grimes awakens from a coma to find a post-apocalyptic world dominated by flesh-eating zombies. He sets out to find his family and encounters many other survivors along the way.",
            "889.031","en"))
        tvShow.add(TvShowEntity("4",
            "Rick and Morty",
            "/8kOWDBK6XlPUzckuHDo3wwVRFwt.jpg",
            "Rick is a mentally-unbalanced but scientifically gifted old man who has recently reconnected with his family. He spends most of his time involving his young grandson Morty in dangerous, outlandish adventures throughout space and alternate universes. Compounded with Morty's already unstable family life, these events cause Morty much distress at home and school.",
            "797.194","en"))
        tvShow.add(TvShowEntity("5",
            "Anupamaa",
            "/9R4Qtm01pNh0CzdoIyvHKefe5RL.jpg",
            "Anupama had to sacrifice a lot to become an ideal wife, daughter-in-law, and mother. After a bitter realisation, she sets out to live on her own terms.",
            "715.324","hi"))
        tvShow.add(TvShowEntity("6",
            "Tokyo Revengers",
            "/1VZ8gN6tPoAd7EdfCzVPmtDczhD.jpg\"",
            "Takemichi Hanagaki is a freelancer that’s reached the absolute pits of despair in his life. He finds out that the only girlfriend he ever had, in middle school, Hinata Tachibana, had been killed by the ruthless Tokyo Manji Gang. The day after hearing about her death, he’s standing on the station platform and ends up being pushed over onto the tracks by a herd of people. He closes his eyes thinking he’s about to die, but when he opens his eyes back up, he somehow had gone back in time 12 years. Now that he’s back living the best days of his life, Takemichi decides to get revenge on his life.",
            "402.935","jp"))
        tvShow.add(TvShowEntity("7",
            "O Amor Acontece",
            "/aqvOIR4CzCQETw9pAuyXZYKd2Ni.jpg",
            "Originally from the Netherlands, Let Love Rule now has a Portuguese version. Each week, four new stories are shown, lived by different protagonists that we meet every Sunday. At the end of four days, a meeting dictates the participants' continuation as a couple - outside the experience - or their separation. In a ceremony, led by Maria Cerqueira Gomes and Pedro Teixeira, the couples evaluate their experience. Mafalda de Castro presents the diaries of this format, which is premised on helping singles find love.",
            "329.89","pt"))
        tvShow.add(TvShowEntity("8",
            "Miraculous: Tales of Ladybug & Cat Noir",
            "/9RqliZcoDEjSEISeA0LY9meAiNv.jpg",
            "Normal high school kids by day, protectors of Paris by night! Miraculous follows the heroic adventures of Marinette and Adrien as they transform into Ladybug and Cat Noir and set out to capture akumas, creatures responsible for turning the people of Paris into villains. But neither hero knows the other’s true identity – or that they’re classmates!",
            "278.659","fr"))
        tvShow.add(TvShowEntity("9",
            "Yeh Rishta Kya Kehlata Hai",
            "/jJpyrPNvPTNK5Y3qdYWZ5BDeg0b.jpg\"",
            "Naitik and Akshara Singhania live in a Marwari joint family in Udaipur. As a young married couple, they journey through adjustment issues, later as parents of young children, and they learn to love each other and their respective extended families while becoming mature members of their household.",
            "238.768","hi"))
        tvShow.add(TvShowEntity(
            "10",
            "/xBYfKcbDY0NktGn9f7QW8qaV96g.jpg",
            "Fena: Pirate Princess",
            "Fena Houtman remembers little about her childhood. Orphaned and raised as a servant in a brothel, her life changes when she escapes to an island of pirates where she discovers the truth behind her family. With Fena being the only one able to unlock her family’s secrets, and a formidable crew of female pirates on her tail, she must take her place as captain of her Samurai crew for a high seas adventure!",
            "205.135","ja"
        ))
        return tvShow
    }
    fun generateShow() : List<TvShowResource>{
        val tvShow = ArrayList<TvShowResource>()
        tvShow.add(TvShowResource(1,
            "The Bond",
            "/zN8vBX1jDRxIDqFQ2ARcodTHhdt.jpg",
            "",
            1504.291,"zh"))
        tvShow.add(TvShowResource(2,
            "Ilha Record",
            "/zN8vBX1jDRxIDqFQ2ARcodTHhdt.jpg",
            "Ilha Record is a Brazilian reality television competition format originally created and aired by RecordTV. The series is hosted by Sabrina Sato and premiered on Monday",
            1449.274,"pt"))
        tvShow.add(TvShowResource(3,
            "The Walking Dead",
            "/w21lgYIi9GeUH5dO8l3B9ARZbCB.jpg",
            "Sheriff's deputy Rick Grimes awakens from a coma to find a post-apocalyptic world dominated by flesh-eating zombies. He sets out to find his family and encounters many other survivors along the way.",
            889.031,"en"))
        tvShow.add(TvShowResource(4,
            "Rick and Morty",
            "/8kOWDBK6XlPUzckuHDo3wwVRFwt.jpg",
            "Rick is a mentally-unbalanced but scientifically gifted old man who has recently reconnected with his family. He spends most of his time involving his young grandson Morty in dangerous, outlandish adventures throughout space and alternate universes. Compounded with Morty's already unstable family life, these events cause Morty much distress at home and school.",
            797.194,"en"))
        tvShow.add(TvShowResource(5,
            "Anupamaa",
            "/9R4Qtm01pNh0CzdoIyvHKefe5RL.jpg",
            "Anupama had to sacrifice a lot to become an ideal wife, daughter-in-law, and mother. After a bitter realisation, she sets out to live on her own terms.",
            715.324,"hi"))
        tvShow.add(TvShowResource(6,
            "Tokyo Revengers",
            "/1VZ8gN6tPoAd7EdfCzVPmtDczhD.jpg\"",
            "Takemichi Hanagaki is a freelancer that’s reached the absolute pits of despair in his life. He finds out that the only girlfriend he ever had, in middle school, Hinata Tachibana, had been killed by the ruthless Tokyo Manji Gang. The day after hearing about her death, he’s standing on the station platform and ends up being pushed over onto the tracks by a herd of people. He closes his eyes thinking he’s about to die, but when he opens his eyes back up, he somehow had gone back in time 12 years. Now that he’s back living the best days of his life, Takemichi decides to get revenge on his life.",
            402.935,"jp"))
        tvShow.add(TvShowResource(7,
            "O Amor Acontece",
            "/aqvOIR4CzCQETw9pAuyXZYKd2Ni.jpg",
            "Originally from the Netherlands, Let Love Rule now has a Portuguese version. Each week, four new stories are shown, lived by different protagonists that we meet every Sunday. At the end of four days, a meeting dictates the participants' continuation as a couple - outside the experience - or their separation. In a ceremony, led by Maria Cerqueira Gomes and Pedro Teixeira, the couples evaluate their experience. Mafalda de Castro presents the diaries of this format, which is premised on helping singles find love.",
            329.89,"pt"))
        tvShow.add(TvShowResource(8,
            "Miraculous: Tales of Ladybug & Cat Noir",
            "/9RqliZcoDEjSEISeA0LY9meAiNv.jpg",
            "Normal high school kids by day, protectors of Paris by night! Miraculous follows the heroic adventures of Marinette and Adrien as they transform into Ladybug and Cat Noir and set out to capture akumas, creatures responsible for turning the people of Paris into villains. But neither hero knows the other’s true identity – or that they’re classmates!",
            278.659,"fr"))
        tvShow.add(TvShowResource(9,
            "Yeh Rishta Kya Kehlata Hai",
            "/jJpyrPNvPTNK5Y3qdYWZ5BDeg0b.jpg\"",
            "Naitik and Akshara Singhania live in a Marwari joint family in Udaipur. As a young married couple, they journey through adjustment issues, later as parents of young children, and they learn to love each other and their respective extended families while becoming mature members of their household.",
            238.768,"hi"))
        tvShow.add(TvShowResource(
            10,
            "/xBYfKcbDY0NktGn9f7QW8qaV96g.jpg",
            "Fena: Pirate Princess",
            "Fena Houtman remembers little about her childhood. Orphaned and raised as a servant in a brothel, her life changes when she escapes to an island of pirates where she discovers the truth behind her family. With Fena being the only one able to unlock her family’s secrets, and a formidable crew of female pirates on her tail, she must take her place as captain of her Samurai crew for a high seas adventure!",
            205.135,"ja"
        ))
        return tvShow
    }

    fun generateFavoriteMovie(): List<MyFavoriteMovie> {
        val movie = ArrayList<MyFavoriteMovie>()

        movie.add(MyFavoriteMovie("1","The Tomorrow War",
            ConstVal.IMG_URL + "/yizL4cEKsVvl17Wc1mGEIrQtM2F.jpg",
            "The world is stunned when a group of time travelers arrive from the year 2051 to deliver an urgent message: Thirty years in the future, mankind is losing a global war against a deadly alien species. The only hope for survival is for soldiers and civilians from the present to be transported to the future and join the fight. Among those recruited is high school teacher and family man Dan Forester. Determined to save the world for his young daughter, Dan teams up with a brilliant scientist and his estranged father in a desperate quest to rewrite the fate of the planet.",
            "2878.154","en"))
        movie.add(MyFavoriteMovie("2",
            "The Suicide Squad",
            "/iXbWpCkIauBMStSTUT9v4GXvdgH.jpg",
            "Supervillains Harley Quinn, Bloodsport, Peacemaker and a collection of nutty cons at Belle Reve prison join the super-secret, super-shady Task Force X as they are dropped off at the remote, enemy-infused island of Corto Maltese.",
            "3184.405","en"))
        movie.add(MyFavoriteMovie("3",
            "The Forever Purge",
            "/lB068qa6bQ0QKYKyC2xnYGvYjl7.jpg",
            "All the rules are broken as a sect of lawless marauders decides that the annual Purge does not stop at daybreak and instead should never end as they chase a group of immigrants who they want to punish because of their harsh historical past.",
            "687.349","en"))
        movie.add(MyFavoriteMovie("4","Shang-Chi and the Legend of the Ten Rings",
            "/nDLylQOoIazGyYuWhk21Yww5FCb.jpg",
            "Shang-Chi must confront the past he thought he left behind when he is drawn into the web of the mysterious Ten Rings organization.",
            "2878.154","en"))
        movie.add(MyFavoriteMovie("5","Jungle Cruise",
            "/9dKCd55IuTT5QRs989m9Qlb7d2B.jpg",
            "Dr. Lily Houghton enlists the aid of wisecracking skipper Frank Wolff to take her down the Amazon in his dilapidated boat. Together, they search for an ancient tree that holds the power to heal – a discovery that will change the future of medicine.",
            "2189.931","en"))

        return movie
//        var favMovie = MyFavoriteMovie(
//            "9","Free Guy",
//            "/hEqw9swA8gFJuNjgWYEypwZfkZg.jpg",
//            " bank teller called Guy realizes he is a background character in an open world video game called Free City that will soon go offline",
//            "2878.154","en"
//        )
//        return favMovie
    }

    fun generateFavoriteTv(): List<MyFavoriteTvShow> {
        val tvShow = ArrayList<MyFavoriteTvShow>()

        tvShow.add(MyFavoriteTvShow("1",
            "The Bond",
            "/zN8vBX1jDRxIDqFQ2ARcodTHhdt.jpg",
            "",
            "1504.291","zh"))
        tvShow.add(MyFavoriteTvShow("2",
            "Ilha Record",
            "/zN8vBX1jDRxIDqFQ2ARcodTHhdt.jpg",
            "Ilha Record is a Brazilian reality television competition format originally created and aired by RecordTV. The series is hosted by Sabrina Sato and premiered on Monday",
            "1449.274","pt"))
        tvShow.add(MyFavoriteTvShow("3",
            "The Walking Dead",
            "/w21lgYIi9GeUH5dO8l3B9ARZbCB.jpg",
            "Sheriff's deputy Rick Grimes awakens from a coma to find a post-apocalyptic world dominated by flesh-eating zombies. He sets out to find his family and encounters many other survivors along the way.",
            "889.031","en"))
        return tvShow
//        var favMovie = MyFavoriteTvShow(
//            "1",
//            "The Bond",
//            "/zN8vBX1jDRxIDqFQ2ARcodTHhdt.jpg",
//            "",
//            "1504.291","zh")
//        return favMovie
    }

    fun generateGenreShow() : List<Genre>{
        val genreShow = ArrayList<Genre>()

        genreShow.add(Genre(1,"Drama"))
        genreShow.add(Genre(1,"Family"))
        genreShow.add(Genre(2,"Reality"))
        genreShow.add(Genre(3,"Action & Adventure"))
        genreShow.add(Genre(3,"Drama"))
        genreShow.add(Genre(3,"Sci-Fi & Fantasy"))
        genreShow.add(Genre(4,"Animation"))
        genreShow.add(Genre(4,"Comedy"))
        genreShow.add(Genre(4,"Sci-Fi & Fantasy"))
        genreShow.add(Genre(4,"Action & Adventure"))
        genreShow.add(Genre(5,"Drama"))
        genreShow.add(Genre(5,"Soap"))
        genreShow.add(Genre(6,"Drama"))
        genreShow.add(Genre(6,"Animation"))
        genreShow.add(Genre(6,"Action & Adventure"))
        genreShow.add(Genre(7,"Reality"))
        genreShow.add(Genre(8,"Action & Adventure"))
        genreShow.add(Genre(8,"Animation"))
        genreShow.add(Genre(8,"Kids"))
        genreShow.add(Genre(9,"Drama"))
        genreShow.add(Genre(9,"Family"))
        genreShow.add(Genre(9,"Soap"))
        genreShow.add(Genre(10,"Animation"))
        genreShow.add(Genre(10,"Action & Adventure"))
        genreShow.add(Genre(10,"Sci-Fi & Fantasy"))


        return genreShow
    }
}