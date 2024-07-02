package main.service;

import main.entity.Rating;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Transactional
public class RatingServiceJPA implements RatingService{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void setRating(Rating rating) throws RatingException {

        Rating existingRating = entityManager.createNamedQuery("Rating.getExistRating", Rating.class)
                .setParameter("game", rating.getGame())
                .setParameter("player", rating.getPlayer())
                .getResultList()
                .stream()
                .findFirst()
                .orElse(null);


        if (existingRating != null) {
            existingRating.setRating(rating.getRating());
            existingRating.setRatingon(rating.getRatingon());
            entityManager.merge(existingRating);
        }
        else {
            entityManager.persist(rating);
        }
    }

    @Override
    public int getAverageRating(String game) throws RatingException {
        var result = entityManager.createNamedQuery("Rating.getAverageRating")
                .setParameter("game", game).getSingleResult();
        return result == null ? 0 : (int) Math.round((double) result);
    }

    @Override
    public int getRating(String game, String player) throws RatingException {
        return (int) entityManager.createNamedQuery("Rating.getRating")
                .setParameter("game", game).setParameter( "player", player).getSingleResult();
    }

    @Override
    public void reset() throws RatingException {
        entityManager.createNamedQuery("Rating.resetScores").executeUpdate();
    }
}
