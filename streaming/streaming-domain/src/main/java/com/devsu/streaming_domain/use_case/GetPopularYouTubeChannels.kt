package com.devsu.streaming_domain.use_case

import com.devsu.streaming_domain.model.YouTubeChannel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetPopularYouTubeChannels {
    fun execute(
    ): Flow<List<YouTubeChannel>> = flow{
        emit(
            listOf(
                YouTubeChannel(
                    channelId = "UClas8qQXLvhiO-ycFjQvFjw",
                    name = "Devsu",
                    title = "Jumpstart Your Digital Innovation",
                    subtitle = "Scale your tech teams and projects with expert nearshore and local software development talent",
                    background = "https://media.glassdoor.com/l/1363932/devsu-1675788118474.png",
                    logo = "https://pbs.twimg.com/profile_images/1290741633714184193/5Wvjlhc8_400x400.jpg",
                ),
                YouTubeChannel(
                    channelId = "UCGAWuGsaWysuMHbj0w2-Ffw",
                    name = "Taco Bell",
                    title = "GET A FULL MEAL JUST FOR SIGNING UP",
                    subtitle = "For a limited time, join Taco Bell Rewards to get a Build Your Own Cravings Box for \$1** and redeem your free Welcome Reward* on the app.",
                    background = "https://images.axios.com/xXpfp16wDUo6HPfXh-D1a6kFGw4=/0x0:1920x1080/1920x1080/2021/08/12/1628795286976.jpg",
                    logo = "https://play-lh.googleusercontent.com/-hKUbOPtydte2A9jkGwlijoPy1qnOktdEKhEl9leWxDoqYtbKhsDe3_rrMPTOV__5w",
                ),
                YouTubeChannel(
                    channelId = "",
                    name = "Deutsch LA",
                    title = "We care the most\n" +
                            "about each other, our work,\n" +
                            "and our clients.",
                    subtitle = "We are a data-inspired, culturally shaped creative studio.",
                    background = "https://deutschla.com/static/b89c98c518bc55f2da55737422e39bcb/d6abd/dla-smileys_0.webp",
                    logo = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQgcLPf02mVuMHXR9RFpQHG4LIqFtHIAjmG1m-jCLKNkA&s",
                ),
                YouTubeChannel(
                    channelId = "UCZyCIVBPurwji32wx4MeuMw",
                    name = "Banco Pichincha",
                    title = "Tu dinero disponible cuando quieras.",
                    subtitle = "Mantén tu dinero seguro",
                    background = "https://masfinanzas.com.pe/wp-content/uploads/2021/01/Banco-Pichincha.jpg",
                    logo = "https://i.ibb.co/2gxW4Xp/40116303-2036865229697854-9075671977015902208-n.png",
                ),
                YouTubeChannel(
                    channelId = "UCVg_sYcMgzN08BJlmHJjWZA",
                    name = "Fiducia",
                    title = "NUESTROS FONDOS DE INVERSIÓN\n" +
                            "SON LA FORMA DE CONSEGUIR TUS METAS",
                    subtitle = "Nuestros fondos reducen el riesgo diversificando el portafolio de inversiones en varias instituciones, rigurosamente seleccionadas para brindar la mayor seguridad.",
                    background = "https://i.ibb.co/NFSsYrs/425848254-705898111658792-345306380441483549-n-1.jpg",
                    logo = "https://i.ibb.co/Sf4L3gc/425761798-704783698436900-2682186981372553487-n.jpghttps://i.ibb.co/2gxW4Xp/40116303-2036865229697854-9075671977015902208-n.png",
                ),
                YouTubeChannel(
                    channelId = "UCVg_sYcMgzN08BJlmHJjWZA",
                    name = "MrBeast",
                    title = "SUBSCRIBE FOR A COOKIE!",
                    subtitle = "X Super Official CEO",
                    background = "https://www.snackandbakery.com/ext/resources/ci/2022/01/31/MrBeast-Feastables.png?1670345226",
                    logo = "https://static.wikia.nocookie.net/dream_team/images/7/79/Mrbeastlogo.jpg/revision/latest?cb=20230125153030",
                ),
                YouTubeChannel(
                    channelId = "UCqECaJ8Gagnn7YCbPEzWH6g",
                    name = "Taylor Swift",
                    title = "THE ERAS TOUR",
                    subtitle = "All’s fair in love and poetry. New album THE TORTURED POETS DEPARTMENT. Out April 19 \uD83E\uDD0D",
                    background = "https://i0.wp.com/libertytheatrenc.com/wp-content/uploads/2023/09/2_Landscape-Hero-Art_16x9_3000.jpg?resize=2048%2C1152&ssl=1",
                    logo = "https://pbs.twimg.com/profile_images/1758452321644650496/tGEf4QFK_400x400.jpg",
                ),
            )
        )
    }
}