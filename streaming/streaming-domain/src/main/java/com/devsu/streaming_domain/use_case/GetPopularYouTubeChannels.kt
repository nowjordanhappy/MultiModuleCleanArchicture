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
                    logo = "https://scontent-ord5-2.xx.fbcdn.net/v/t1.6435-9/40116303_2036865229697854_9075671977015902208_n.png?_nc_cat=104&ccb=1-7&_nc_sid=7a1959&_nc_ohc=rbKEYbsner8AX8kz1LS&_nc_ht=scontent-ord5-2.xx&oh=00_AfByXoUyXlepKuRERY_heML0sbP7U-7SFCmVf2J0oIjdSA&oe=65E8C72A",
                ),
                YouTubeChannel(
                    channelId = "UCVg_sYcMgzN08BJlmHJjWZA",
                    name = "Fiducia",
                    title = "NUESTROS FONDOS DE INVERSIÓN\n" +
                            "SON LA FORMA DE CONSEGUIR TUS METAS",
                    subtitle = "Nuestros fondos reducen el riesgo diversificando el portafolio de inversiones en varias instituciones, rigurosamente seleccionadas para brindar la mayor seguridad.",
                    background = "https://scontent-ord5-2.xx.fbcdn.net/v/t39.30808-6/419090853_690009349914335_4058447540919684730_n.jpg?_nc_cat=103&ccb=1-7&_nc_sid=3635dc&_nc_ohc=hrH8GPeJizkAX_Szist&_nc_ht=scontent-ord5-2.xx&oh=00_AfBx_xFl5l0bZKJrCLUvUSreP_AYPw4FSZP9G9kl-ZQsNg&oe=65C6A8E2",
                    logo = "https://scontent-ord5-1.xx.fbcdn.net/v/t39.30808-6/425761798_704783698436900_2682186981372553487_n.jpg?_nc_cat=108&ccb=1-7&_nc_sid=3635dc&_nc_ohc=4hc_oyihS4IAX8Ybe_R&_nc_ht=scontent-ord5-1.xx&oh=00_AfDFNkcqD2ca_c9KxlRiWbqhJ17wXzcU0DewmHuMgCVkSA&oe=65D977A3",
                ),
            )
        )
    }
}