//Imports
import java.io.File
import java.util.Random

//Helper class for generating random numbers
class RandomNumberGenerator(val seed: Long) {
    private val random = Random(seed)
    
    fun nextInt(min: Int, max: Int) : Int {
        return random.nextInt(max - min + 1) + min
    }
    
    fun nextDouble(min: Double, max: Double) : Double {
        return random.nextDouble() * (max - min) + min
    }
    
    fun nextBoolean() : Boolean {
        return random.nextBoolean()
    }
}

//Main class for the live music venue
class LiveMusicVenue {
    private val randomNumberGenerator = RandomNumberGenerator(seed = 12345)
    var capacity: Int = 0
    val performers: MutableList<Performer> = mutableListOf()
    val bands: MutableList<Band> = mutableListOf()
    
    fun addPerformer(name: String, genre: String): Performer {
        val performer = Performer(name, genre)
        performers.add(performer)
        return performer
    }
    
    fun addBand(name: String, genre: String, numberOfMembers: Int): Band {
        val band = Band(name, genre, numberOfMembers)
        bands.add(band)
        return band
    }
    
    fun setCapacity(newCapacity: Int) {
        if (newCapacity < 0) {
            throw IllegalArgumentException("Capacity cannot be negative!")
        }
        capacity = newCapacity
    }
    
    fun allowRecordings(): Boolean {
        return randomNumberGenerator.nextBoolean()
    }
}

//Abstract class of performers
abstract class Performer(val name: String, val genre: String) {
    abstract fun perform()
    
    override fun toString(): String {
        return "Performer(name='$name', genre='$genre')"
    }
}

//Concrete class of a solo performer
class SoloPerformer(name: String, genre: String) : Performer(name, genre) {
    override fun perform() {
        println("$name is performing their solo $genre set!")
    }
}

//Concrete class of a band
class Band(name: String, genre: String, val numberOfMembers: Int) : Performer(name, genre) {
    override fun perform() {
        println("$name is performing their $genre set as a $numberOfMembers-piece band!")
    }
}

//Main function to run the program
fun main() {
    val venue = LiveMusicVenue()
    venue.setCapacity(100)
    
    val soloPerformer = venue.addPerformer("John Smith", "Jazz")
    val band = venue.addBand("The Band", "Rock", 4)
    
    soloPerformer.perform()
    band.perform()
    
    println("Allow Recordings? ${venue.allowRecordings()}")
}