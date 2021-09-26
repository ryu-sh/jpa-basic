package jpql;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("hello");

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        try {
            Member member = new Member();
            member.setUserName("member1");
            member.setAge(10);

            Team team = new Team();
            team.setName("team1");

            member.setTeam(team);

            entityManager.persist(team);
            entityManager.persist(member);

            entityManager.flush();
            entityManager.clear();

            /*                  쿼리 결과 조회
            Member member1 = entityManager.createQuery("select m from Member m where m.userName = :username", Member.class)
                    .setParameter("username", "member1")
                    .getSingleResult();
            List<Member> resultList = query1.getResultList();

            TypedQuery<String> query2 = entityManager.createQuery("select m.userName from Member m", String.class);
            String singleResult = query2.getSingleResult();*/

            /*                  스칼라 타입 조회
            List<Object[]> resultList = entityManager.createQuery("select m.userName, m.age from Member m")
                    .getResultList();
            Object[] object = resultList.get(0);
            System.out.println("username = " + object[0]);
            System.out.println("age = " + object[1]);

            List<MemberDTO> resultList = entityManager.createQuery("select new com.example.jpql.domain.MemberDTO(m.userName, m.age) from Member m", MemberDTO.class)
                    .getResultList();

             */

            /*          영속성 컨텍스트에 관리된다.
            List<Member> members = entityManager.createQuery("select m from Member m", Member.class)
                    .getResultList();*/

            /*          페이징 처리
            List<Member> resultList = entityManager.createQuery("select m from Member m order by m.age desc", Member.class)
                    .setFirstResult(0)
                    .setMaxResults(10)
                    .getResultList();

            for (Member member1 : resultList) {
                System.out.println("member1 = " + member1.getAge());
            }*/

            /*          조인
            List<Member> resultList = entityManager.createQuery("select m from Member m inner join m.team t", Member.class)
                    .getResultList();*/

            /*          조건식
            String query = "select " +
                    "case when m.age <= 10 then '학생 요금'" +
                    "     when m.age >= 60 then '경로 요금'" +
                    "     else                  '일반 요금'" +
                    "end from Member m";

            String query = "select coalesce(m.userName, '이름없는 회원') from Member m";
            List<String> resultList = entityManager.createQuery(query, String.class)
                    .getResultList();

            for (String s : resultList) {
                System.out.println("s = " + s);
            }*/

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
