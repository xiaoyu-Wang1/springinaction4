package orders;

import orders.config.MongoConfig;
import orders.db.OrderRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

@ContextConfiguration(classes=MongoConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class MongoDbTest2 {
	@Autowired MongoOperations mongoOps;
	
	@Before
	public void cleanup() {
		// Deleting all orders (just in case something is left over from a previous failed run)
		mongoOps.dropCollection("order");
	}
	
	@Test
	public void testMongo() {
		mongoOps.save(createAnOrder());
		System.out.println(mongoOps.getCollection("order").getCount());
		System.out.println(mongoOps.find(Query.query(Criteria.where("type").is("WEB")), Order.class));
	}

	private Order createAnOrder() {
		Order order = new Order();
		order.setCustomer("Chuck Wagon");
		order.setType("WEB");
		Item item1 = new Item();
		item1.setProduct("Spring in Action");
		item1.setQuantity(2);
		item1.setPrice(29.99);
		Item item2 = new Item();
		item2.setProduct("Module Java");
		item2.setQuantity(31);
		item2.setPrice(29.95);
		order.setItems(Arrays.asList(item1, item2));
		return order;
	}
	
}
