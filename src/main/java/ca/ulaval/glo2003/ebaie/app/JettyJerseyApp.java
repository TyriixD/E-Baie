package ca.ulaval.glo2003.ebaie.app;

import ca.ulaval.glo2003.ebaie.CreationUrl;
import ca.ulaval.glo2003.ebaie.buyer.api.BuyerRessource;
import ca.ulaval.glo2003.ebaie.buyer.api.assemblers.BuyerDtoAssembler;
import ca.ulaval.glo2003.ebaie.buyer.application.BuyerUseCase;
import ca.ulaval.glo2003.ebaie.buyer.application.assembler.BuyerAssembler;
import ca.ulaval.glo2003.ebaie.buyer.entities.BuyerFactory;
import ca.ulaval.glo2003.ebaie.buyer.entities.BuyerRepository;
import ca.ulaval.glo2003.ebaie.buyer.infrastructure.persistance.BuyerRepositoryImplementation;
import ca.ulaval.glo2003.ebaie.health.HealthRessource;
import ca.ulaval.glo2003.ebaie.inventory.api.InventoryException.InvalidProductDurationExceptionMapper;
import ca.ulaval.glo2003.ebaie.inventory.api.InventoryException.InvalidProductIdExceptionMapper;
import ca.ulaval.glo2003.ebaie.inventory.api.InventoryException.InvalidProductInitialPriceExceptionMapper;
import ca.ulaval.glo2003.ebaie.inventory.api.ItemDtoAssembler;
import ca.ulaval.glo2003.ebaie.inventory.api.ItemResource;
import ca.ulaval.glo2003.ebaie.inventory.application.ItemUseCase;
import ca.ulaval.glo2003.ebaie.inventory.application.assemblers.ItemAssembler;
import ca.ulaval.glo2003.ebaie.inventory.entities.InventoryRepository;
import ca.ulaval.glo2003.ebaie.inventory.entities.ItemFactory;
import ca.ulaval.glo2003.ebaie.inventory.infrastructure.persistence.InMemoryInventoryRepositoryImp;
import ca.ulaval.glo2003.ebaie.inventory.infrastructure.persistence.MongoDBInventoryRepositoryImp;
import ca.ulaval.glo2003.ebaie.offer.api.OfferResource;
import ca.ulaval.glo2003.ebaie.offer.api.assemblers.OfferDtoAssembler;
import ca.ulaval.glo2003.ebaie.offer.application.OfferUseCase;
import ca.ulaval.glo2003.ebaie.offer.application.assembler.OfferAssembler;
import ca.ulaval.glo2003.ebaie.offer.entities.OfferFactory;
import ca.ulaval.glo2003.ebaie.offer.entities.OfferRepository;
import ca.ulaval.glo2003.ebaie.offer.infrastructure.persistence.OfferRepositoryImp;
import ca.ulaval.glo2003.ebaie.review.api.ReviewResources;
import ca.ulaval.glo2003.ebaie.review.api.assembler.ReviewDtoAssembler;
import ca.ulaval.glo2003.ebaie.review.application.ReviewUseCase;
import ca.ulaval.glo2003.ebaie.review.application.assembler.ReviewAssembler;
import ca.ulaval.glo2003.ebaie.review.entities.ReviewFactory;
import ca.ulaval.glo2003.ebaie.review.entities.ReviewRepository;
import ca.ulaval.glo2003.ebaie.review.infrastructure.persistence.ReviewRepositoryImp;
import ca.ulaval.glo2003.ebaie.seller.api.SellerResource;
import ca.ulaval.glo2003.ebaie.seller.api.assemblers.SellerDtoAssembler;
import ca.ulaval.glo2003.ebaie.seller.api.dtos.InvalidSellerIdExceptionMapper;
import ca.ulaval.glo2003.ebaie.seller.application.SellerUseCase;
import ca.ulaval.glo2003.ebaie.seller.application.assembler.SellerAssembler;
import ca.ulaval.glo2003.ebaie.seller.entities.SellerFactory;
import ca.ulaval.glo2003.ebaie.seller.entities.SellerRepository;
import ca.ulaval.glo2003.ebaie.seller.infrastructure.persistence.InMemorySellerRepositoryImp;
import ca.ulaval.glo2003.ebaie.seller.infrastructure.persistence.MongoDBSellerRepositoryImp;
import ca.ulaval.glo2003.ebaie.shared.api.dtos.*;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandlerCollection;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;

import javax.ws.rs.core.Application;
import java.net.InetSocketAddress;
import java.util.HashSet;
import java.util.Set;

public class JettyJerseyApp implements EBaieApplication {

    private final Server server;

    public JettyJerseyApp(CreationUrl url, String profile) {
        InventoryRepository inventoryRepository;
        SellerRepository sellerRepository;

        //initialize repositories
        if (profile.equals("staging") || profile.equals("production")) {
            inventoryRepository = new MongoDBInventoryRepositoryImp();
            sellerRepository = new MongoDBSellerRepositoryImp();
        } else { //dev
            inventoryRepository = new InMemoryInventoryRepositoryImp();
            sellerRepository = new InMemorySellerRepositoryImp();
        }

        //Item request
        ItemFactory itemFactory = new ItemFactory();
        ItemAssembler itemAssembler = new ItemAssembler();
        ItemUseCase itemUseCase = new ItemUseCase(itemFactory, inventoryRepository, itemAssembler);


        //Seller request
        SellerFactory sellerFactory = new SellerFactory();

        SellerAssembler sellerAssembler = new SellerAssembler();
        SellerUseCase sellerUseCase =
                new SellerUseCase(sellerFactory, sellerRepository, sellerAssembler);
        ItemDtoAssembler itemDtoAssembler = new ItemDtoAssembler(sellerUseCase);
        ItemResource itemResource = new ItemResource(itemUseCase, itemDtoAssembler, url);
        SellerDtoAssembler sellerDtoAssembler = new SellerDtoAssembler();
        SellerResource sellerResource = new SellerResource(sellerUseCase, sellerDtoAssembler, url);


        //Offer request
        OfferFactory offerFactory = new OfferFactory();

        OfferAssembler offerAssembler = new OfferAssembler();
        OfferRepository offerRepository = new OfferRepositoryImp();
        OfferUseCase offerUseCase =
                new OfferUseCase(offerFactory, offerRepository, offerAssembler);

        //Health request
        HealthRessource healthRessource = new HealthRessource();

        //Buyer request
        BuyerFactory buyerFactory = new BuyerFactory();

        BuyerAssembler buyerAssembler = new BuyerAssembler();
        BuyerRepository buyerRepository = new BuyerRepositoryImplementation();
        BuyerUseCase buyerUseCase =
                new BuyerUseCase(buyerFactory, buyerRepository, buyerAssembler);

        BuyerDtoAssembler buyerDtoAssembler = new BuyerDtoAssembler(offerUseCase, itemUseCase);
        BuyerRessource buyerRessource = new BuyerRessource(buyerUseCase, buyerDtoAssembler, url);

        OfferDtoAssembler offerDtoAssembler = new OfferDtoAssembler(itemUseCase, buyerUseCase);
        OfferResource offerResource = new OfferResource(offerUseCase, offerDtoAssembler, url);

        //review
        ReviewFactory reviewFactory = new ReviewFactory();
        ReviewAssembler reviewAssembler = new ReviewAssembler();
        ReviewRepository reviewRepository = new ReviewRepositoryImp();
        ReviewUseCase reviewUseCase =
                new ReviewUseCase(reviewFactory, reviewRepository, reviewAssembler);
        ReviewDtoAssembler reviewDtoAssembler = new ReviewDtoAssembler(sellerUseCase);
        ReviewResources reviewResources = new ReviewResources(reviewUseCase, reviewDtoAssembler, url);

        // Setup api endpoints
        ServletContextHandler contextHandler =
                new ServletContextHandler(ServletContextHandler.SESSIONS);
        contextHandler.setContextPath("/api");
        ResourceConfig resourceConfig = ResourceConfig.forApplication(new Application() {
            @Override
            public Set<Object> getSingletons() {
                Set<Object> resources = new HashSet<>();
                resources.add(itemResource);
                resources.add(new InvalidProductIdExceptionMapper());
                resources.add(new InvalidProductDurationExceptionMapper());
                resources.add(new InvalidProductInitialPriceExceptionMapper());
                resources.add(sellerResource);
                resources.add(new FieldEmptyExceptionMapper());
                resources.add(new GlobalExceptionMapper());
                resources.add(new InvalidDateTimeExceptionMapper());
                resources.add(new InvalidSellerIdExceptionMapper());
                resources.add(new InvalidAmountExceptionMapper());
                resources.add(new BiddingEndedExceptionMapper());

                resources.add(buyerRessource);
                resources.add(new InvalidDateExceptionMapper());
                resources.add(new InvalidBuyerIdExceptionMapper());
                resources.add(offerResource);
                resources.add(healthRessource);
                resources.add(new TitelLengthExceptionMapper());
                resources.add(new RatingExceptionMapper());
                resources.add(reviewResources);
                return resources;
            }
        });

        // Setup server
        ServletContainer servletContainer = new ServletContainer(resourceConfig);
        ServletHolder servletHolder = new ServletHolder(servletContainer);
        contextHandler.addServlet(servletHolder, "/*");

        ContextHandlerCollection contexts = new ContextHandlerCollection();
        contexts.setHandlers(new Handler[]{contextHandler});
        server = new Server(new InetSocketAddress(url.getHostname(), url.getPort()));
        server.setHandler(contexts);
    }

    @Override
    public void start() {
        try {
            server.start();
            server.join();
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            server.destroy();
        }
    }
}
