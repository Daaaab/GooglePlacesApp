package jkuc.futureprocessing.fp.googleplacesapp.data.net;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import java.io.IOException;

import jkuc.futureprocessing.fp.googleplacesapp.data.entity.Result;
import rx.Observable;

public class MockDownloader implements IPlacesDownloader {

    @Override
    public Observable<Result> getNearbyBars(String location, int radius, String type, String key) {
        Moshi moshi = new Moshi.Builder().build();
        JsonAdapter<Result> adapter = moshi.adapter(Result.class);

        try {
            return Observable.just(adapter.fromJson(getPlacesJSON()));
        } catch(IOException e) {
            return Observable.just(new Result("ERROR", null));
        }
    }
//
    private String getPlacesJSON(){

        return "{\n"
                + "   \"html_attributions\" : [],\n"
                + "   \"results\" : [\n"
                + "      {\n"
                + "         \"geometry\" : {\n"
                + "            \"location\" : {\n"
                + "               \"lat\" : 50.2756732,\n"
                + "               \"lng\" : 18.6981672\n"
                + "            },\n"
                + "            \"viewport\" : {\n"
                + "               \"northeast\" : {\n"
                + "                  \"lat\" : 50.2757824,\n"
                + "                  \"lng\" : 18.69826245\n"
                + "               },\n"
                + "               \"southwest\" : {\n"
                + "                  \"lat\" : 50.27534559999999,\n"
                + "                  \"lng\" : 18.69788144999999\n"
                + "               }\n"
                + "            }\n"
                + "         },\n"
                + "         \"icon\" : \"https://maps.gstatic.com/mapfiles/place_api/icons/bar-71.png\",\n"
                + "         \"id\" : \"632cc05f5f5394255f8628e2a4ece7b1f3d76c90\",\n"
                + "         \"name\" : \"Bar Gil\",\n"
                + "         \"opening_hours\" : {\n"
                + "            \"open_now\" : true,\n"
                + "            \"weekday_text\" : []\n"
                + "         },\n"
                + "         \"photos\" : [\n"
                + "            {\n"
                + "               \"height\" : 867,\n"
                + "               \"html_attributions\" : [\n"
                + "                  \"\\u003ca href=\\\"https://maps.google.com/maps/contrib/103564192946195344892/photos\\\"\\u003eMC Qux\\u003c/a\\u003e\"\n"
                + "               ],\n"
                + "               \"photo_reference\" : "
                + "\"CoQBdwAAABYvKuc-KlZp-cyOHB0DdJuUQCsMImQqRWWZkHg_TSB4tJU7b6ow829jok4p-sQYdlo5el7s4RZZb8i5oBXlzH8Gz_riykzDs_EFRxch9xgd5qBXTiWHb-NoNzo1kobp8RBvpI9wid"
                + "-097TvEd3zJ7gPK3c28knC55YHaQtd_TMpEhAy6yhn4I30ZimAlwHh0zHTGhSAobHgh2FVGCZibw0bB-IXLvmNGw\",\n"
                + "               \"width\" : 1300\n"
                + "            }\n"
                + "         ],\n"
                + "         \"place_id\" : \"ChIJ15Tbp2wxEUcRKHoo_WVB_dI\",\n"
                + "         \"rating\" : 4.9,\n"
                + "         \"reference\" : \"CmRSAAAAVtHQhyaJcN9WFd6Z-ifOt0hTcvpYrwzAgk0MygAtV-s2X-enZRDbCFnuKOxBVaiPQBnIRR2n_7KOoA43bjO"
                + "-9xEZatKBAUKF0AN8q_eRWjg4WKLxYiVYdEyrsWBRDCZzEhDQSAONW5KRIPsUJw2S5FFOGhRHZhW8YxTY1ieKFGIEOlE_QyA8SQ\",\n"
                + "         \"scope\" : \"GOOGLE\",\n"
                + "         \"types\" : [ \"bar\", \"point_of_interest\", \"establishment\" ],\n"
                + "         \"vicinity\" : \"Pszczyńska 243, Gliwice\"\n"
                + "      },\n"
                + "      {\n"
                + "         \"geometry\" : {\n"
                + "            \"location\" : {\n"
                + "               \"lat\" : 50.2858604,\n"
                + "               \"lng\" : 18.6754664\n"
                + "            },\n"
                + "            \"viewport\" : {\n"
                + "               \"northeast\" : {\n"
                + "                  \"lat\" : 50.28597964999999,\n"
                + "                  \"lng\" : 18.67569755\n"
                + "               },\n"
                + "               \"southwest\" : {\n"
                + "                  \"lat\" : 50.28582065000001,\n"
                + "                  \"lng\" : 18.67538935\n"
                + "               }\n"
                + "            }\n"
                + "         },\n"
                + "         \"icon\" : \"https://maps.gstatic.com/mapfiles/place_api/icons/bar-71.png\",\n"
                + "         \"id\" : \"ca454d5169fa6fa5d7546d10baa67e8b60f23c0c\",\n"
                + "         \"name\" : \"HELLGATE\",\n"
                + "         \"opening_hours\" : {\n"
                + "            \"open_now\" : true,\n"
                + "            \"weekday_text\" : []\n"
                + "         },\n"
                + "         \"photos\" : [\n"
                + "            {\n"
                + "               \"height\" : 1079,\n"
                + "               \"html_attributions\" : [\n"
                + "                  \"\\u003ca href=\\\"https://maps.google.com/maps/contrib/110985671744529670425/photos\\\"\\u003eHELLGATE\\u003c/a\\u003e\"\n"
                + "               ],\n"
                + "               \"photo_reference\" : "
                +
                "\"CoQBdwAAAO75Pn8kJktRHWcrb7UrHiXXXexcUZI30sFbgLIxIQLwSYzogQ0QW_Zh7RVPyj5FL1qKi85zqMYzuLMZL1diFx15frrcQ8CdsqoX9BJsfkaV8lZlRIXlI7_2v4VtkMH9seJDByu4wwQd5LLxp_E0OfEVMyNEWPd-v6SsP1LmVihhEhDCVS3PztwSm-AnKscGgRoAGhSkLMwQgvk43ROgMSeDOMTP4-IDzA\",\n"
                + "               \"width\" : 1920\n"
                + "            }\n"
                + "         ],\n"
                + "         \"place_id\" : \"ChIJ77td6AIxEUcRTxP9NJSBPhA\",\n"
                + "         \"rating\" : 4.6,\n"
                + "         \"reference\" : "
                + "\"CmRRAAAAXY_3m79IUXeuSTe046gkEy"
                +
                "-cAR_4i0O7BDuAWhEls3vaX80hRiyNbmGtdkFaeur_IuvyjvdJf8c8cfmLEQsjrE0xgZhzZUU6bc19J5jI5MzIkKIFMUt1MSAKWIx5d6yBEhDcpfJ7qd0e20pvHZ3CmvvZGhTGlPJQTTkIYcc4_myJjNQwdxprZg"
                + "\",\n"
                + "         \"scope\" : \"GOOGLE\",\n"
                + "         \"types\" : [ \"bar\", \"point_of_interest\", \"establishment\" ],\n"
                + "         \"vicinity\" : \"Pszczyńska 80, Gliwice\"\n"
                + "      },\n"
                + "      {\n"
                + "         \"geometry\" : {\n"
                + "            \"location\" : {\n"
                + "               \"lat\" : 50.28556589999999,\n"
                + "               \"lng\" : 18.6790759\n"
                + "            },\n"
                + "            \"viewport\" : {\n"
                + "               \"northeast\" : {\n"
                + "                  \"lat\" : 50.28594594999999,\n"
                + "                  \"lng\" : 18.6794841\n"
                + "               },\n"
                + "               \"southwest\" : {\n"
                + "                  \"lat\" : 50.28442575000002,\n"
                + "                  \"lng\" : 18.6778513\n"
                + "               }\n"
                + "            }\n"
                + "         },\n"
                + "         \"icon\" : \"https://maps.gstatic.com/mapfiles/place_api/icons/generic_business-71.png\",\n"
                + "         \"id\" : \"3caa5d4d69b06b65be9d5a79c0fb1692869ef4e0\",\n"
                + "         \"name\" : \"\\\"IWO\\\" Przedsiębiorstwo Usługowo-Handlowe\",\n"
                + "         \"opening_hours\" : {\n"
                + "            \"open_now\" : true,\n"
                + "            \"weekday_text\" : []\n"
                + "         },\n"
                + "         \"place_id\" : \"ChIJXQ9lbwMxEUcR64O9S0B2V3k\",\n"
                + "         \"reference\" : \"CmRRAAAAYij9h66kFtZYl2PjqRmz6baG3pAVkLsJQIXZLOQbe-S1mxa7xqJ7ry5mctzvr"
                + "-e4KmtHflvkbTsx87CQma_UIokuMx60QC1MMB9CxASz1bEmy4FHdIaAgbq17Qol_Ct6EhCFnocfiFaoFkaoK6x7SKDZGhTWSvmJ-x4ICZA2EekxJuVgKC1OmQ\",\n"
                + "         \"scope\" : \"GOOGLE\",\n"
                + "         \"types\" : [ \"bar\", \"food\", \"point_of_interest\", \"establishment\" ],\n"
                + "         \"vicinity\" : \"Pszczyńska 89A, Gliwice\"\n"
                + "      },\n"
                + "      {\n"
                + "         \"geometry\" : {\n"
                + "            \"location\" : {\n"
                + "               \"lat\" : 50.2890158,\n"
                + "               \"lng\" : 18.6740625\n"
                + "            },\n"
                + "            \"viewport\" : {\n"
                + "               \"northeast\" : {\n"
                + "                  \"lat\" : 50.28966364999999,\n"
                + "                  \"lng\" : 18.6742662\n"
                + "               },\n"
                + "               \"southwest\" : {\n"
                + "                  \"lat\" : 50.28879985,\n"
                + "                  \"lng\" : 18.67345139999999\n"
                + "               }\n"
                + "            }\n"
                + "         },\n"
                + "         \"icon\" : \"https://maps.gstatic.com/mapfiles/place_api/icons/bar-71.png\",\n"
                + "         \"id\" : \"7aee6edef279c6432d9a919dc564d6aca5514995\",\n"
                + "         \"name\" : \"Bar Wiedeński\",\n"
                + "         \"opening_hours\" : {\n"
                + "            \"open_now\" : true,\n"
                + "            \"weekday_text\" : []\n"
                + "         },\n"
                + "         \"photos\" : [\n"
                + "            {\n"
                + "               \"height\" : 930,\n"
                + "               \"html_attributions\" : [\n"
                + "                  \"\\u003ca href=\\\"https://maps.google.com/maps/contrib/118079728735715721925/photos\\\"\\u003eAlicja Chmielniak\\u003c/a\\u003e\"\n"
                + "               ],\n"
                + "               \"photo_reference\" : "
                + "\"CoQBdwAAALuojiNgvun91smOs5ToKy4pHM8rT9HhIH5PRyKC1qmV3BlyM9EoGFQiKbSH4MnrLp_qWsaPJ0Y5RMnp5hiWlkbHRHbOltYxWiyxdefGf94Zq3LjHV81f"
                + "-cddLdjOTEgwIcWPmERQtjIFdSuEEVwMtKL7pJJumDhUqGsUIItMiJbEhBffNmMgUG6shNvgEjiWttIGhS9-JQ--zS1kyU7GaNEIB6pizeTtA\",\n"
                + "               \"width\" : 720\n"
                + "            }\n"
                + "         ],\n"
                + "         \"place_id\" : \"ChIJQTEN7_0wEUcR3uXRNBAyqKc\",\n"
                + "         \"reference\" : "
                + "\"CmRSAAAATeDI2EjOk99zZeefM7jelMApHGBjbnTE5luoow09aN-SBLC45Z4r-3oVDSbjtxJbdxop4Zgw89LkSL_X9h0_3aZnp5UpO8uRkMIGcU3GaAGvUeicob83bzz7EZPhw7aqEhC1eYy01J1qjo"
                + "-cR8GVyzxaGhSkjeXLnDwBMEK-H3WXi5IZwz68bg\",\n"
                + "         \"scope\" : \"GOOGLE\",\n"
                + "         \"types\" : [ \"bar\", \"food\", \"point_of_interest\", \"establishment\" ],\n"
                + "         \"vicinity\" : \"Łużycka 16, Gliwice\"\n"
                + "      },\n"
                + "      {\n"
                + "         \"geometry\" : {\n"
                + "            \"location\" : {\n"
                + "               \"lat\" : 50.28935339999999,\n"
                + "               \"lng\" : 18.6735338\n"
                + "            },\n"
                + "            \"viewport\" : {\n"
                + "               \"northeast\" : {\n"
                + "                  \"lat\" : 50.2894962,\n"
                + "                  \"lng\" : 18.6738323\n"
                + "               },\n"
                + "               \"southwest\" : {\n"
                + "                  \"lat\" : 50.28930579999999,\n"
                + "                  \"lng\" : 18.6734343\n"
                + "               }\n"
                + "            }\n"
                + "         },\n"
                + "         \"icon\" : \"https://maps.gstatic.com/mapfiles/place_api/icons/bar-71.png\",\n"
                + "         \"id\" : \"c71a30eaa7bfc278120271929516d8ad64ed2e2a\",\n"
                + "         \"name\" : \"Podwórko\",\n"
                + "         \"opening_hours\" : {\n"
                + "            \"open_now\" : true,\n"
                + "            \"weekday_text\" : []\n"
                + "         },\n"
                + "         \"place_id\" : \"ChIJTzEN7_0wEUcRFicrhheptgk\",\n"
                + "         \"reference\" : \"CmRRAAAAWF-GjTTszR78EjG4lGx0GC9QqUA9s_TlSfQi3xnV9onMB7u8krv2CXCGFoxnS5IRrFFygQ2ydXyMSOa2DU6nS6gUe-rIBb8kEeIqpdMFGB-qmtm3"
                + "-mokVHGeCQw40geTEhC-zRNo5d-snUthU3cS_A3tGhQXJkoGIAwFH29GYpdY3_49eiP5Cw\",\n"
                + "         \"scope\" : \"GOOGLE\",\n"
                + "         \"types\" : [ \"bar\", \"point_of_interest\", \"establishment\" ],\n"
                + "         \"vicinity\" : \"Łużycka 16, Gliwice\"\n"
                + "      },\n"
                + "      {\n"
                + "         \"geometry\" : {\n"
                + "            \"location\" : {\n"
                + "               \"lat\" : 50.28918890000001,\n"
                + "               \"lng\" : 18.6661511\n"
                + "            },\n"
                + "            \"viewport\" : {\n"
                + "               \"northeast\" : {\n"
                + "                  \"lat\" : 50.28933454999999,\n"
                + "                  \"lng\" : 18.66619515\n"
                + "               },\n"
                + "               \"southwest\" : {\n"
                + "                  \"lat\" : 50.28914035,\n"
                + "                  \"lng\" : 18.66601895\n"
                + "               }\n"
                + "            }\n"
                + "         },\n"
                + "         \"icon\" : \"https://maps.gstatic.com/mapfiles/place_api/icons/bar-71.png\",\n"
                + "         \"id\" : \"28bfcda8355a0e2f58259ff1b8369df02d0bac89\",\n"
                + "         \"name\" : \"Andros. Bar\",\n"
                + "         \"place_id\" : \"ChIJ0UQXhPswEUcRjwMhBXLyRVU\",\n"
                + "         \"reference\" : \"CmRRAAAAD_liWwWXKSmbNRypIWveDcqpZ5Oa7EXgQHNztynRCjfmleeoJ8zLh1xz7RA35t7lXfFueoZZ-kxQybNPR1oUZ"
                + "-wXmMZ6_dTKqYITDCJUl7egjVVY30OUJzTDGN60cEw2EhCZ5d5LfC8qBu5_oylrLedXGhQ3FglczQFmMBEYIUNhu08B2jg-Sw\",\n"
                + "         \"scope\" : \"GOOGLE\",\n"
                + "         \"types\" : [ \"bar\", \"point_of_interest\", \"establishment\" ],\n"
                + "         \"vicinity\" : \"Nowy Świat 9, Gliwice\"\n"
                + "      },\n"
                + "      {\n"
                + "         \"geometry\" : {\n"
                + "            \"location\" : {\n"
                + "               \"lat\" : 50.28492199999999,\n"
                + "               \"lng\" : 18.6756172\n"
                + "            },\n"
                + "            \"viewport\" : {\n"
                + "               \"northeast\" : {\n"
                + "                  \"lat\" : 50.28493994999999,\n"
                + "                  \"lng\" : 18.67586485\n"
                + "               },\n"
                + "               \"southwest\" : {\n"
                + "                  \"lat\" : 50.28486815,\n"
                + "                  \"lng\" : 18.67553465\n"
                + "               }\n"
                + "            }\n"
                + "         },\n"
                + "         \"icon\" : \"https://maps.gstatic.com/mapfiles/place_api/icons/bar-71.png\",\n"
                + "         \"id\" : \"d5f9cfd5c2562fb6800e3ac12126b806f4c84edf\",\n"
                + "         \"name\" : \"Harvis Pub\",\n"
                + "         \"place_id\" : \"ChIJCzK11wIxEUcRRo6FiY7o4wQ\",\n"
                + "         \"reference\" : \"CmRRAAAAKKAjiU5BQTOU9V9cfeaoBZipcKfI6gn0GAhLABNUV-sPeSTJiaZ7yBqcRGS0KL5vL0DehtwKRVhuCkeAq68G7vqShTSsC3C6Z9z5IJ5zyiet8W5Yt9n42_uF1V"
                + "-KvxgPEhBrduzxubsglkgialWi8U4TGhT_IaTU5Zelh2MyzAq9Nctdp4Go9g\",\n"
                + "         \"scope\" : \"GOOGLE\",\n"
                + "         \"types\" : [ \"bar\", \"point_of_interest\", \"establishment\" ],\n"
                + "         \"vicinity\" : \"Bojkowska 6, Gliwice\"\n"
                + "      },\n"
                + "      {\n"
                + "         \"geometry\" : {\n"
                + "            \"location\" : {\n"
                + "               \"lat\" : 50.2883243,\n"
                + "               \"lng\" : 18.6743381\n"
                + "            },\n"
                + "            \"viewport\" : {\n"
                + "               \"northeast\" : {\n"
                + "                  \"lat\" : 50.2888445,\n"
                + "                  \"lng\" : 18.67500634999999\n"
                + "               },\n"
                + "               \"southwest\" : {\n"
                + "                  \"lat\" : 50.28815090000001,\n"
                + "                  \"lng\" : 18.67411535\n"
                + "               }\n"
                + "            }\n"
                + "         },\n"
                + "         \"icon\" : \"https://maps.gstatic.com/mapfiles/place_api/icons/bar-71.png\",\n"
                + "         \"id\" : \"6431e641e410446cb7ba1b8a9086525238569b50\",\n"
                + "         \"name\" : \"Stolowka & Restauracja \\\"Na Łużyckiej\\\"\",\n"
                + "         \"opening_hours\" : {\n"
                + "            \"open_now\" : true,\n"
                + "            \"weekday_text\" : []\n"
                + "         },\n"
                + "         \"photos\" : [\n"
                + "            {\n"
                + "               \"height\" : 1125,\n"
                + "               \"html_attributions\" : [\n"
                + "                  \"\\u003ca href=\\\"https://maps.google.com/maps/contrib/102580963115667785774/photos\\\"\\u003eStolowka &amp; Restauracja &quot;Na "
                + "Łużyckiej&quot;\\u003c/a\\u003e\"\n"
                + "               ],\n"
                + "               \"photo_reference\" : \"CoQBdwAAADpoZB3Srh5AJZKEbvx0K0_xYsfz80OV-RzB46eiToImaGBd6_im1Qw-q6SIvUaNAZIa8NZTj-Uvo8a7Ip50z2fLCwj-1FcGj9F"
                + "-hcZllYs8v6mXlFaj8T5DRdTSoImfsM2MrYNqzP6fzdD4VkMBo4P4lZFQlY5iJUGkLnej4YIKEhDFY3kFoiF1lol8txvYQM4vGhTgRK39VxsjbftlPulRPAj9Yi3soA\",\n"
                + "               \"width\" : 1500\n"
                + "            }\n"
                + "         ],\n"
                + "         \"place_id\" : \"ChIJRRxpCwIxEUcRl8IM_nFyZEA\",\n"
                + "         \"reference\" : "
                + "\"CmRRAAAAk_HY-e0z2LkicIpbhgskUAIsXCsjQy4-Mx7htwdWFCgYyQXuRMditKCvKHvgJmxFT8T5yKiIGk_SYAEve1dQDUeB9jFa3SzDzi9hI_b3Scpu0D2OAMwCcO_cBRfhQLlcEhDqPMpx"
                + "-gxEHnjRgFtbMkKtGhRhDQwQ9kfeKGMhkD-Tw9bL8uhZkg\",\n"
                + "         \"scope\" : \"GOOGLE\",\n"
                + "         \"types\" : [ \"bar\", \"point_of_interest\", \"establishment\" ],\n"
                + "         \"vicinity\" : \"Łużycka 24, Gliwice\"\n"
                + "      },\n"
                + "      {\n"
                + "         \"geometry\" : {\n"
                + "            \"location\" : {\n"
                + "               \"lat\" : 50.29167169999999,\n"
                + "               \"lng\" : 18.6829725\n"
                + "            }\n"
                + "         },\n"
                + "         \"icon\" : \"https://maps.gstatic.com/mapfiles/place_api/icons/bar-71.png\",\n"
                + "         \"id\" : \"6cbf9071329e02bf060952b7f08c14953e7e9e94\",\n"
                + "         \"name\" : \"Festo Biuro Gliwice\",\n"
                + "         \"place_id\" : \"ChIJv4qZ2wAxEUcRsygdVMEViVE\",\n"
                + "         \"reference\" : \"CmRRAAAAdGo3L67L8bQ2Zx0dL3h18H9rax9mjYa2LOuwZiM_a78xgqr2x-VuetXhhDZVN-WUyyxl_7eoPAVRTqhG_9Je6SwMmt3wb68iUQqhxuhO15i9taaJJn_K0iiw1"
                + "-1ANsYYEhBWKtcOHrZM0Pgzmv23QdLIGhRMdZVxmqXZixMT8-govnm5AxD23A\",\n"
                + "         \"scope\" : \"GOOGLE\",\n"
                + "         \"types\" : [ \"bar\", \"point_of_interest\", \"establishment\" ],\n"
                + "         \"vicinity\" : \"Stanisława Konarskiego 18C, Gliwice\"\n"
                + "      },\n"
                + "      {\n"
                + "         \"geometry\" : {\n"
                + "            \"location\" : {\n"
                + "               \"lat\" : 50.2889907,\n"
                + "               \"lng\" : 18.6687948\n"
                + "            },\n"
                + "            \"viewport\" : {\n"
                + "               \"northeast\" : {\n"
                + "                  \"lat\" : 50.28915600000001,\n"
                + "                  \"lng\" : 18.6690204\n"
                + "               },\n"
                + "               \"southwest\" : {\n"
                + "                  \"lat\" : 50.2889356,\n"
                + "                  \"lng\" : 18.6687196\n"
                + "               }\n"
                + "            }\n"
                + "         },\n"
                + "         \"icon\" : \"https://maps.gstatic.com/mapfiles/place_api/icons/restaurant-71.png\",\n"
                + "         \"id\" : \"b1ea6630aec023ba937c0bdb6b0fe516bee7fcae\",\n"
                + "         \"name\" : \"MATRIX PUB\",\n"
                + "         \"place_id\" : \"ChIJmcPoQvwwEUcRxQEWQk-0-H0\",\n"
                + "         \"reference\" : \"CmRRAAAAOGr6kU9cemOTXkoPXnBrQFMTa8x4BXIHgMEja70SaSpDrGKySkvYZZAbudhnWbelpnfdikHQ7rVavvnrmfJRzp9Y7Z0W7YWQ60be_Kx2y1tSC0Pg8_be87_0l"
                + "-ndGkI1EhDHf1RmycHpRBfrcKplBKUBGhQaI_wTFmcjCnKvnd3atD-OcUU7AQ\",\n"
                + "         \"scope\" : \"GOOGLE\",\n"
                + "         \"types\" : [ \"bar\", \"restaurant\", \"food\", \"point_of_interest\", \"establishment\" ],\n"
                + "         \"vicinity\" : \"Pszczyńska 14, Gliwice\"\n"
                + "      },\n"
                + "      {\n"
                + "         \"geometry\" : {\n"
                + "            \"location\" : {\n"
                + "               \"lat\" : 50.276129,\n"
                + "               \"lng\" : 18.6579887\n"
                + "            }\n"
                + "         },\n"
                + "         \"icon\" : \"https://maps.gstatic.com/mapfiles/place_api/icons/bar-71.png\",\n"
                + "         \"id\" : \"98132a29a11019fa15baecc571cea7a3e58093fe\",\n"
                + "         \"name\" : \"GIT Bar\",\n"
                + "         \"opening_hours\" : {\n"
                + "            \"open_now\" : true,\n"
                + "            \"weekday_text\" : []\n"
                + "         },\n"
                + "         \"photos\" : [\n"
                + "            {\n"
                + "               \"height\" : 2160,\n"
                + "               \"html_attributions\" : [\n"
                + "                  \"\\u003ca href=\\\"https://maps.google.com/maps/contrib/113707691517444741664/photos\\\"\\u003eAnna Gawłowska\\u003c/a\\u003e\"\n"
                + "               ],\n"
                + "               \"photo_reference\" : "
                + "\"CoQBdwAAAMLZHIla0WS1PxpgiJOZEOscyD9nhtDH-InFidzJi_jTNa2T4423AxbgE6zBSYKGpu1eMhDgW-HBIRhn4Hnh8GPxv3DH68uuQDwLDqvhpxBtjDMJQgJLEZzFAYeCsp-BOFMHQbdb-ffME8qV1Y"
                + "-B0LugDUdUh6Wah3TMJmKo-c07EhCuFgRFmUQNvxMUuDLXysdTGhS436ZcanzSUe48WPDYzaPUFeyCiQ\",\n"
                + "               \"width\" : 3840\n"
                + "            }\n"
                + "         ],\n"
                + "         \"place_id\" : \"ChIJc-CvB94wEUcReFwWye1l3ic\",\n"
                + "         \"rating\" : 4.8,\n"
                + "         \"reference\" : \"CmRRAAAAmxm5NDjhmLVoKjsLKz0B-br8bmEZektU98-FtQrKHobqU7hGGNt2rBgXw6y4EcYElgFwKX"
                + "-iCkszuZJLMN6YbAuBXFydL0grVZn3mAZpRdwDfZMcz0atWCZL4Ut0cXU_EhB_dhIBpXiE6EjY2nlDKp0dGhQ6R1o-NV1uLwcsSxl3joKDQ8cR8A\",\n"
                + "         \"scope\" : \"GOOGLE\",\n"
                + "         \"types\" : [ \"night_club\", \"bar\", \"point_of_interest\", \"establishment\" ],\n"
                + "         \"vicinity\" : \"Gliwice\"\n"
                + "      },\n"
                + "      {\n"
                + "         \"geometry\" : {\n"
                + "            \"location\" : {\n"
                + "               \"lat\" : 50.27910999999999,\n"
                + "               \"lng\" : 18.7124687\n"
                + "            },\n"
                + "            \"viewport\" : {\n"
                + "               \"northeast\" : {\n"
                + "                  \"lat\" : 50.27935375000001,\n"
                + "                  \"lng\" : 18.71263275\n"
                + "               },\n"
                + "               \"southwest\" : {\n"
                + "                  \"lat\" : 50.27902874999999,\n"
                + "                  \"lng\" : 18.71197655\n"
                + "               }\n"
                + "            }\n"
                + "         },\n"
                + "         \"icon\" : \"https://maps.gstatic.com/mapfiles/place_api/icons/restaurant-71.png\",\n"
                + "         \"id\" : \"cfb566101d4fd2c822a775e5d5bc8086cd88c825\",\n"
                + "         \"name\" : \"Zadymiacz.pl Wędzarnia Ryb i Mięs\",\n"
                + "         \"photos\" : [\n"
                + "            {\n"
                + "               \"height\" : 2988,\n"
                + "               \"html_attributions\" : [\n"
                + "                  \"\\u003ca href=\\\"https://maps.google.com/maps/contrib/103410301701765994401/photos\\\"\\u003eJerzy Rzepka\\u003c/a\\u003e\"\n"
                + "               ],\n"
                + "               \"photo_reference\" : "
                + "\"CoQBdwAAABKUw9J8piA0uzmWqC74hEskR2LU8HSJXVKB1cg1NvR3"
                + "-pjiaxil0f_5tTIPZt0PzWWkLTnQObQGFsihMe4WakWyD3hqYlK43mqKdkNKhGqI7amSNV4FJZYsL41CWY4gu2LnzyuQEOq8YT8WfActeYgjZ298fMLG2AwAeXQ"
                + "-iHwvEhAfF_cFEru5VNazuDwSmgwoGhS0H0Uuc6UNp4nIBYHG3Guh3kfPPQ\",\n"
                + "               \"width\" : 5312\n"
                + "            }\n"
                + "         ],\n"
                + "         \"place_id\" : \"ChIJVT1_MHAxEUcRLC-oFFL-rEM\",\n"
                + "         \"reference\" : \"CmRRAAAAItpzjzTtzvOy6IHtnXcmBGMUrfrSx0DpxY9RSaR3JtttrS-qMDlQ3HCqY3EK1oayChUSHHDtKbuZPE"
                + "-jvh5_2HZt_XviscrkelvKKNrVrw_qJ9ApKCkzwU8oU2fobVPuEhDA3001fezmUuuJfuva_8tdGhSDyZ8TllXLdhptJYhpiznXZLm5FA\",\n"
                + "         \"scope\" : \"GOOGLE\",\n"
                + "         \"types\" : [ \"bar\", \"restaurant\", \"food\", \"point_of_interest\", \"establishment\" ],\n"
                + "         \"vicinity\" : \"Błonie, Gliwice\"\n"
                + "      }\n"
                + "   ],\n"
                + "   \"status\" : \"OK\"\n"
                + "}";
    }

}
