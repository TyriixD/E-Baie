package ca.ulaval.glo2003.ebaie.seller.infrastructure.persistence;

import ca.ulaval.glo2003.ebaie.seller.entities.Seller;
import ca.ulaval.glo2003.ebaie.seller.infrastructure.persistence.SellerDtoMongoDB;
import ca.ulaval.glo2003.ebaie.valueobjects.SellerId;
import ca.ulaval.glo2003.ebaie.valueobjects.PriceAmount;
import ca.ulaval.glo2003.ebaie.valueobjects.SellerId;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class SellerMongoDBAssembler {

    public Seller fromDto(SellerDtoMongoDB mongoDto) {

        return new Seller(
                mongoDto.name,
                mongoDto.description,
                new SellerId(mongoDto.sellerId),
                Instant.parse(mongoDto.createdAt)
        );
    }

    public List<Seller> fromDtoList(List<SellerDtoMongoDB> sellerDtoMongoDBList) {
        List<Seller> sellerList = new ArrayList<>();
        for (SellerDtoMongoDB sellerDtoMongoDB : sellerDtoMongoDBList) {
            sellerList.add(fromDto(sellerDtoMongoDB));
        }

        return sellerList;
    }

    public SellerDtoMongoDB toDto(Seller seller) {
        SellerDtoMongoDB sellerDtoMongoDB = new SellerDtoMongoDB();
        sellerDtoMongoDB.sellerId = seller.getId().getValue();
        sellerDtoMongoDB.name = seller.getName();
        sellerDtoMongoDB.description = seller.getDescription();
        sellerDtoMongoDB.createdAt = seller.getCreatedAt().toString();

        return sellerDtoMongoDB;
    }
}
