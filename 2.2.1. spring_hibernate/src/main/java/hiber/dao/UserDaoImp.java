package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
        return query.getResultList();
    }

    @Override
    public User findUserByCar(Car car) {
        String hql = "from User as user where user.car.model=:carModel and user.car.series=:carSeries";

        Query<User> query = sessionFactory.getCurrentSession().createQuery(hql)
                .setParameter("carModel", car.getModel())
                .setParameter("carSeries", car.getSeries());

        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            User user = new User();
            user.setCar(new Car());
            return user;
        }

    }
}
