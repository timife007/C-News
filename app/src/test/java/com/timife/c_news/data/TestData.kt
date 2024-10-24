package com.timife.c_news.data

import com.timife.c_news.data.local.entities.ArticleEntity
import com.timife.c_news.data.network.model.ArticleDto
import com.timife.c_news.data.network.model.SourceDto
import com.timife.c_news.domain.model.Article

object TestData {
    val vmQueryResult = Article(
        title = "5 Yoga Poses to Increase Flexibility",
        description = "Improve your flexibility with these beginner-friendly yoga poses.",
        imageUrl = "https://example.com/images/yoga_poses.jpg",
        publishedAt = "14th March, 2023",
        source = "Wellness Daily",
        content = "Yoga poses like Downward Dog and Cobra can improve flexibility.",
        author = "Emily Clark"
    )
    val articles = listOf(
        ArticleEntity(
            title = "Mastering the Basics of Basketball Dribbling",
            description = "Learn the essential dribbling techniques to outplay your opponents.",
            imageUrl = "https://example.com/images/basketball_dribbling.jpg",
            publishedAt = "12th January, 2022",
            source = "Sports Weekly",
            content = "Dribbling is a crucial skill in basketball that requires hours of practice.",
            author = "Jane Doe"
        ),
        ArticleEntity(
            title = "Top 5 Workouts for Footballers",
            description = "Get stronger and faster with these workouts.",
            imageUrl = "https://example.com/images/football_workouts.jpg",
            publishedAt = "5th February, 2021",
            source = "Fitness Insider",
            content = "Football players benefit from explosive strength and endurance workouts.",
            author = "John Smith"
        ),
        ArticleEntity(
            title = "The Mental Benefits of Swimming",
            description = "Discover how swimming can improve your mental health.",
            imageUrl = "https://example.com/images/swimming_benefits.jpg",
            publishedAt = "8th June, 2020",
            source = "Healthline",
            content = "Swimming is not only good for the body but also the mind.",
            author = "Sarah Connor"
        ),
        ArticleEntity(
            title = "5 Yoga Poses to Increase Flexibility",
            description = "Improve your flexibility with these beginner-friendly yoga poses.",
            imageUrl = "https://example.com/images/yoga_poses.jpg",
            publishedAt = "14th March, 2023",
            source = "Wellness Daily",
            content = "Yoga poses like Downward Dog and Cobra can improve flexibility.",
            author = "Emily Clark"
        ),
        ArticleEntity(
            title = "How to Improve Your Marathon Time",
            description = "Tips and tricks to run a faster marathon.",
            imageUrl = "https://example.com/images/marathon_training.jpg",
            publishedAt = "30th November, 2021",
            source = "Runners World",
            content = "Training for a marathon requires proper planning and pacing strategies.",
            author = "Alex Wright"
        ),
        ArticleEntity(
            title = "Top 10 Benefits of Cycling Every Day",
            description = "Cycling offers numerous health benefits.",
            imageUrl = "https://example.com/images/cycling_benefits.jpg",
            publishedAt = "21st April, 2022",
            source = "Bicycle Times",
            content = "Daily cycling can boost cardiovascular health and improve mood.",
            author = "Liam Evans"
        ),
        ArticleEntity(
            title = "How to Get Started with Weightlifting",
            description = "Begin your weightlifting journey with these tips.",
            imageUrl = "https://example.com/images/weightlifting_tips.jpg",
            publishedAt = "19th May, 2023",
            source = "Fitness Guide",
            content = "Weightlifting is a great way to build strength and muscle.",
            author = "Chris Brown"
        ),
        ArticleEntity(
            title = "The Science Behind Recovery Days",
            description = "Why rest days are essential for athletes.",
            imageUrl = "https://example.com/images/recovery_days.jpg",
            publishedAt = "9th October, 2020",
            source = "Sports Science Journal",
            content = "Recovery is key to avoiding injuries and maximizing performance.",
            author = "Linda Parker"
        ),
        ArticleEntity(
            title = "How to Stay Motivated During Off-Season",
            description = "Keep your fitness levels up even when the season ends.",
            imageUrl = "https://example.com/images/off_season.jpg",
            publishedAt = "18th December, 2021",
            source = "Athlete Hub",
            content = "Motivation can dip during off-seasons, but there are ways to stay on track.",
            author = "Michael Jordan"
        ),
        ArticleEntity(
            title = "The Best Diets for Endurance Athletes",
            description = "Fuel your body for long-distance events.",
            imageUrl = "https://example.com/images/endurance_diet.jpg",
            publishedAt = "2nd February, 2023",
            source = "Nutrition Today",
            content = "Endurance athletes need a mix of carbohydrates, protein, and fats.",
            author = "Anna Lee"
        )
    )

    val articlesDto = listOf(
        ArticleDto(
            title = "Mastering the Basics of Basketball Dribbling",
            description = "Learn the essential dribbling techniques to outplay your opponents.",
            urlToImage = "https://example.com/images/basketball_dribbling.jpg",
            publishedAt = "12th January, 2022",
            url = "",
            source = SourceDto(id = "",name = "Sports Weekly"),
            content = "Dribbling is a crucial skill in basketball that requires hours of practice.",
            author = "Jane Doe"
        ),
        ArticleDto(
            title = "Top 5 Workouts for Footballers",
            description = "Get stronger and faster with these workouts.",
            urlToImage = "https://example.com/images/football_workouts.jpg",
            publishedAt = "5th February, 2021",
            url = "",
            source = SourceDto(id = "",name = "Fitness Insider"),
            content = "Football players benefit from explosive strength and endurance workouts.",
            author = "John Smith"
        ),
        ArticleDto(
            title = "The Mental Benefits of Swimming",
            description = "Discover how swimming can improve your mental health.",
            urlToImage = "https://example.com/images/swimming_benefits.jpg",
            publishedAt = "8th June, 2020",
            source = SourceDto(id = "",name = "Healthline"),
            content = "Swimming is not only good for the body but also the mind.",
            author = "Sarah Connor",
            url = ""
        ),
        ArticleDto(
            title = "5 Yoga Poses to Increase Flexibility",
            description = "Improve your flexibility with these beginner-friendly yoga poses.",
            urlToImage = "https://example.com/images/yoga_poses.jpg",
            publishedAt = "14th March, 2023",
            source = SourceDto(id = "",name = "Wellness Daily"),
            content = "Yoga poses like Downward Dog and Cobra can improve flexibility.",
            author = "Emily Clark",
            url = ""
        ),
        ArticleDto(
            title = "How to Improve Your Marathon Time",
            description = "Tips and tricks to run a faster marathon.",
            urlToImage = "https://example.com/images/marathon_training.jpg",
            publishedAt = "30th November, 2021",
            source = SourceDto(id = "",name = "Runners World"),
            content = "Training for a marathon requires proper planning and pacing strategies.",
            author = "Alex Wright",
            url = ""
        ),
        ArticleDto(
            title = "Top 10 Benefits of Cycling Every Day",
            description = "Cycling offers numerous health benefits.",
            urlToImage = "https://example.com/images/cycling_benefits.jpg",
            publishedAt = "21st April, 2022",
            source = SourceDto(id = "",name = "Bicycle Times"),
            content = "Daily cycling can boost cardiovascular health and improve mood.",
            author = "Liam Evans",
            url = ""

        ),
        ArticleDto(
            title = "How to Get Started with Weightlifting",
            description = "Begin your weightlifting journey with these tips.",
            urlToImage = "https://example.com/images/weightlifting_tips.jpg",
            publishedAt = "19th May, 2023",
            source = SourceDto(id = "",name = "Fitness Guide"),
            content = "Weightlifting is a great way to build strength and muscle.",
            author = "Chris Brown",
            url = ""
        ),
        ArticleDto(
            title = "The Science Behind Recovery Days",
            description = "Why rest days are essential for athletes.",
            urlToImage = "https://example.com/images/recovery_days.jpg",
            publishedAt = "9th October, 2020",
            source = SourceDto(id = "",name = "Sports Science Journal"),
            content = "Recovery is key to avoiding injuries and maximizing performance.",
            author = "Linda Parker",
            url = ""
        ),
        ArticleDto(
            title = "How to Stay Motivated During Off-Season",
            description = "Keep your fitness levels up even when the season ends.",
            urlToImage = "https://example.com/images/off_season.jpg",
            publishedAt = "18th December, 2021",
            source = SourceDto(id = "",name = "Athlete Hub"),
            content = "Motivation can dip during off-seasons, but there are ways to stay on track.",
            author = "Michael Jordan",
            url = ""
        ),
        ArticleDto(
            title = "The Best Diets for Endurance Athletes",
            description = "Fuel your body for long-distance events.",
            urlToImage = "https://example.com/images/endurance_diet.jpg",
            publishedAt = "2nd February, 2023",
            source = SourceDto(id = "", name = "Nutrition Today"),
            content = "Endurance athletes need a mix of carbohydrates, protein, and fats.",
            author = "Anna Lee",
            url = ""
        )
    )


}