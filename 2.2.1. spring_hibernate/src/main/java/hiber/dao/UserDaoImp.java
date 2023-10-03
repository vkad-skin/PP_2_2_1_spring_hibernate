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

    private final SessionFactory sessionFactory;

    @Autowired
    public UserDaoImp(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    public List<User> listUsers() {
        String hqlListUsers = "from User";

        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery(hqlListUsers, User.class);
        return query.getResultList();
    }

    @Override
    public User findUserByCar(Car car) {
        String hqlFindUserByCar = "from User as user where user.car.model=:carModel and user.car.series=:carSeries";

        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery(hqlFindUserByCar, User.class)
                .setParameter("carModel", car.getModel())
                .setParameter("carSeries", car.getSeries());

        return query.getSingleResult();
    }
}
