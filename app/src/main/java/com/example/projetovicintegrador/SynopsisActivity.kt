package com.example.projetovicintegrador

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.projetovicintegrador.databinding.SinopseBinding
import com.example.projetovicintegrador.model.Elenco
import com.example.projetovicintegrador.model.Filme

class SynopsisActivity : AppCompatActivity() {
    // declarando xml numa classe
    private lateinit var binding: SinopseBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // instanciando meu objeto da classe
        binding = SinopseBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.cardView.setBackgroundResource(R.drawable.bg_left_rounded)

        //instanciando objeto Filme
        val movie = creatFakeMovie()
        configViews(movie)
    }

    private fun configViews(movie: Filme) {
        // populando view com valores do objeto
        binding.titleFilm.text = movie.title
        val adapterElenco = ElencoAdapter(movie.listElenco)
        binding.apply {
            year.text = movie.year.toString()
            pg.text = movie.pg
            duration.text = movie.time
            synopsisDescricion.text = movie.biography
            rating.text = movie.rate
            listElenco.adapter = adapterElenco
        }
        //Vou popular os campos do xml

    }

    private fun creatFakeMovie(): Filme {
        //criando objetos do tipo Elenco
        val elenco1 =
            Elenco(photo = "https://i.glbimg.com/og/ig/infoglobo/f/original/2020/11/06/angelica.png",
                nameAuthor = "Rayssa",
                profission = "Diretor")
        val elenco2 =
            Elenco(photo = "https://s2.glbimg.com/BZ0ad9vwxjS_qQV7thVB9KURNAM=/475x475/bottom/i.glbimg.com/og/ig/infoglobo/f/original/2019/10/11/photo-2019-10-11-12-53-54.jpg",
                nameAuthor = "Homem Aranha",
                profission = "Ator")
        val elenco3 =
            Elenco(photo = "https://img.r7.com/images/luciano-huck-27042021164904211?dimensions=677x677&&&&&&&&resize=677x677&crop=678x678+119+111",
                nameAuthor = "Rayssa",
                profission = "Autor")
        val elenco4 =
            Elenco(photo = "https://aaronturatv.ig.com.br/wp-content/uploads/2021/06/xuxa-meneghel-1616766412680_v2_600x600.jpg",
                nameAuthor = "Cruela",
                profission = "Direto")
        val elenco5 =
            Elenco(photo = "https://static1.purepeople.com.br/articles/2/32/18/52/@/3629373-sasha-lembra-casamento-em-foto-com-o-pai-624x600-2.jpg",
                nameAuthor = "Harllen",
                profission = "Cineasta")
        val elenco6 =
            Elenco(photo = "https://upload.wikimedia.org/wikipedia/commons/thumb/c/c5/Giovanna_Antonelli_Boa_Forma.jpg/1200px-Giovanna_Antonelli_Boa_Forma.jpg",
                nameAuthor = "Pedrex",
                profission = "Autora")

        // criando lista com objetos elenco
        val listElenco = listOf(elenco1, elenco2, elenco3, elenco4, elenco5, elenco6)

        // criando e retornando filme com os objetos criados

        return Filme(nameFilm = "exman",
            rate = "50",
            title = "Homem Aranha",
            poster = "",
            listElenco = listElenco,
            genre = arrayListOf("Drama","Ação"),
            favorite = true,
            year = "2020",
            biography = "ksdfjhgldfhsçlkhnsçkjhçdklghmnfkhnçlfgnhlnkhljg",
            synopsis = "gfyugfyusd",
            time = "1h20m",
            pg = "mksadjhfg")

    }
}

