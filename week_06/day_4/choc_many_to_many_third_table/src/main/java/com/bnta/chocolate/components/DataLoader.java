package com.bnta.chocolate.components;

import com.bnta.chocolate.models.Chocolate;
import com.bnta.chocolate.models.CocoaOrder;
import com.bnta.chocolate.models.Estate;
import com.bnta.chocolate.repositories.ChocolateRepository;
import com.bnta.chocolate.repositories.CocoaOrderRepository;
import com.bnta.chocolate.repositories.EstateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {

//    using repositories (not services) for ease as most use DTOs (more suitable for req/res)
    @Autowired
    ChocolateRepository chocolateRepository;

    @Autowired
    EstateRepository estateRepository;

    @Autowired
    CocoaOrderRepository cocoaOrderRepository;

    public DataLoader(){

    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        // RABOT ESTATE
        Estate rabotEstate = new Estate("Rabot Estate", "St Lucia");
        estateRepository.save(rabotEstate);

        Chocolate saltedDark = new Chocolate("Salted Dark", 70);
        chocolateRepository.save(saltedDark);

        CocoaOrder cocoaOrder1 = new CocoaOrder(saltedDark, rabotEstate, "RE254", 47.50);
        cocoaOrderRepository.save(cocoaOrder1);

        Chocolate supermilkHazelnut = new Chocolate("Supermilk Hazelnut", 40);
        chocolateRepository.save(supermilkHazelnut);

        CocoaOrder cocoaOrder2 = new CocoaOrder(supermilkHazelnut, rabotEstate, "RE255", 50.50);
        cocoaOrderRepository.save(cocoaOrder2);


//        // HACIENDA ZOLITA
        Estate haciendaZolita = new Estate("Hacienda Zolita", "Ecuador");
        estateRepository.save(haciendaZolita);

        Chocolate ecuadorDark = new Chocolate("Ecuador Dark", 72);
        chocolateRepository.save(ecuadorDark);

        CocoaOrder cocoaOrder3 = new CocoaOrder(ecuadorDark, haciendaZolita, "HZ641", 50.10);
        cocoaOrderRepository.save(cocoaOrder3);

        Chocolate mintyLove = new Chocolate("Minty Love", 72);
        chocolateRepository.save(mintyLove);

        CocoaOrder cocoaOrder4 = new CocoaOrder(mintyLove, haciendaZolita, "HZ954", 42.00);
        cocoaOrderRepository.save(cocoaOrder4);

//        // KUAPA KOKOO
        Estate kuapaKokoo = new Estate("Kuapa Kokoo", "Ghana");
        estateRepository.save(kuapaKokoo);

        Chocolate pinkSalt = new Chocolate("Dark Pink Himalayan Salt", 60);
        chocolateRepository.save(pinkSalt);

        CocoaOrder cocoaOrder5 = new CocoaOrder(pinkSalt, kuapaKokoo, "KA274", 55.20);
        cocoaOrderRepository.save(cocoaOrder5);

//        // MIXED ORIGIN CHOCOLATE
        Chocolate toffeeTruffle = new Chocolate("Toffee Truffle", 55);
        chocolateRepository.save(toffeeTruffle);

        CocoaOrder cocoaOrder6 = new CocoaOrder(toffeeTruffle, rabotEstate, "RE274", 47.50);
        cocoaOrderRepository.save(cocoaOrder6);

        CocoaOrder cocoaOrder7 = new CocoaOrder(toffeeTruffle, haciendaZolita, "HZ204", 39.70);
        cocoaOrderRepository.save(cocoaOrder7);
        
    }
}
