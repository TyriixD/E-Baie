package ca.ulaval.glo2003.ebaie.inventory.infrastructure.persistence;

import ca.ulaval.glo2003.ebaie.Datastore.DatastoreSingleton;
import ca.ulaval.glo2003.ebaie.inventory.api.InventoryException.InvalidProductIdException;
import ca.ulaval.glo2003.ebaie.inventory.entities.InventoryRepository;
import ca.ulaval.glo2003.ebaie.inventory.entities.Item;
import ca.ulaval.glo2003.ebaie.valueobjects.PriceAmount;
import dev.morphia.Datastore;
import dev.morphia.query.Query;
import dev.morphia.query.experimental.filters.Filters;

import java.util.List;

public class MongoDBInventoryRepositoryImp implements InventoryRepository {

    private final Datastore datastore;
    private InventoryMongoDBAssembler assembler;

    public MongoDBInventoryRepositoryImp() {
        datastore = DatastoreSingleton.getInstance().getDatastore();
        assembler = new InventoryMongoDBAssembler();
    }

    @Override
    public List<Item> findAll() {
        Query<ItemDtoMongoDB> query = datastore.find(ItemDtoMongoDB.class);
        List<ItemDtoMongoDB> itemDtoMongoDBList = query.iterator().toList();

        return assembler.fromDtoList(itemDtoMongoDBList);
    }

    @Override
    public Item findById(String id) {
        Query<ItemDtoMongoDB> query = datastore.find(ItemDtoMongoDB.class).filter(
                Filters.eq("itemId", id)
        );
        ItemDtoMongoDB itemDtoMongoDB = query.first();
        if (itemDtoMongoDB == null) {
            throw new InvalidProductIdException("L'id du produit fourni n'existe pas");
        }

        return assembler.fromDto(itemDtoMongoDB);
    }

    @Override
    public void save(Item item) {
        datastore.save(assembler.toDto(item));
    }

    @Override
    public void updateCurrentPrice(String itemId, double amount) {
        Item item = findById(itemId);
        item.setCurrentPrice(new PriceAmount(amount));
        save(item);
    }
}
