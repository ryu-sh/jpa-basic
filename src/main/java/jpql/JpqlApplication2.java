package jpql;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpqlApplication2 {

    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("hello");

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        try {
            Member member1 = new Member();
            member1.setUserName("member1");
            member1.setAge(10);

            Member member2 = new Member();
            member2.setUserName("member2");
            member2.setAge(10);

            Member member3 = new Member();
            member3.setUserName("member3");
            member3.setAge(10);

            Team teamA = new Team();
            teamA.setName("teamA");
            member1.setTeam(teamA);
            member2.setTeam(teamA);

            Team teamB = new Team();
            teamB.setName("teamB");
            member3.setTeam(teamB);

            entityManager.persist(teamA);
            entityManager.persist(teamB);
            entityManager.persist(member1);
            entityManager.persist(member2);
            entityManager.persist(member3);

            entityManager.flush();
            entityManager.clear();

            /*          페치 조인
            String query = "select m from Member m join fetch m.team";
            List<Member> resultList = entityManager.createQuery(query, Member.class)
                    .getResultList();

            for (Member member : resultList) {
                System.out.println("member.getTeam().getName() = " + member.getTeam().getName());
                //fetch join 안할 경우
                // 회원1, 팀A (SQL)
                // 회원2, 팀A (1차 캐시)
                // 회원3, 팀B (SQL)
                // N + 1 문제, 연관관계된 엔티티를 가져올 떄 발생되는 문제, 전체 쿼리 1번 조회 후 연관된 엔티티 가져오면서 발생한다. fetch join 해야함
            }

            String query1 = "select distinct t from Team t join fetch t.members";
            List<Team> resultList1 = entityManager.createQuery(query1, Team.class)
                    .getResultList();

            for (Team team : resultList1) {
                System.out.println("team size = " + team.getMembers().size());
                // 1 대 다 조회일 경우 데이터가 원래 갯수보다 늘어남.
            }*/

            /*              NamedQuery
            List<Member> resultList = entityManager.createNamedQuery("Member.findByUsername", Member.class)
                    .setParameter("username", "member1")
                    .getResultList();

            for (Member member : resultList) {
                System.out.println("member.getUserName() = " + member.getUserName());
            }*/

            int resultCount = entityManager.createQuery("update Member m set m.age = 20")
                    .executeUpdate();

            System.out.println("resultCount = " + resultCount);


            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            entityManager.close();
        }

        entityManagerFactory.close();
    }
}
