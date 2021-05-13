package ca.ulaval.glo2003.ebaie.seller.infrastructure.persistence;

import ca.ulaval.glo2003.ebaie.Datastore.DatastoreSingleton;
import ca.ulaval.glo2003.ebaie.inventory.api.InventoryException.InvalidProductIdException;
import ca.ulaval.glo2003.ebaie.seller.api.SellerException.InvalidSellerIdException;
import ca.ulaval.glo2003.ebaie.seller.entities.SellerRepository;
import ca.ulaval.glo2003.ebaie.seller.entities.Seller;
import ca.ulaval.glo2003.ebaie.seller.infrastructure.persistence.SellerMongoDBAssembler;
import ca.ulaval.glo2003.ebaie.seller.infrastructure.persistence.SellerDtoMongoDB;
import ca.ulaval.glo2003.ebaie.valueobjects.PriceAmount;
import dev.morphia.Datastore;
import dev.morphia.query.Query;
import dev.morphia.query.experimental.filters.Filters;

import java.util.List;

public class MongoDBSellerRepositoryImp implements SellerRepository {

    private final Datastore datastore;
    private SellerMongoDBAssembler assembler;

    public MongoDBSellerRepositoryImp() {
        datastore = DatastoreSingleton.getInstance().getDatastore();
        assembler = new SellerMongoDBAssembler();
    }

    @Override
    public List<Seller> findAll() {
        Query<SellerDtoMongoDB> query = datastore.find(SellerDtoMongoDB.class);
        List<SellerDtoMongoDB> sellerDtoMongoDBList = query.iterator().toList();

        return assembler.fromDtoList(sellerDtoMongoDBList);
    }

    @Override
    public Seller findById(String id) {
        Query<SellerDtoMongoDB> query = datastore.find(SellerDtoMongoDB.class).filter(
                Filters.eq("sellerId", id)
        );
        SellerDtoMongoDB sellerDtoMongoDB = query.first();
        if (sellerDtoMongoDB == null) {
            throw new InvalidSellerIdException("L'id du Seller fourni n'existe pas");
        }

        return assembler.fromDto(sellerDtoMongoDB);
    }

    @Override
    public void save(Seller seller) {
        datastore.save(assembler.toDto(seller));
    }

}
