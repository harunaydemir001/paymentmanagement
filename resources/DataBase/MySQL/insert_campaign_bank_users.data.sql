INSERT INTO payment.campaign_bank_users (bank_user_id, campaign_id)
SELECT bank_user_id, campaign_id
FROM (
         SELECT b.id AS bank_user_id, c.id AS campaign_id
         FROM payment.bank_users b
                  CROSS JOIN payment.campaigns c
         WHERE b.id BETWEEN 1 AND 20
           AND c.id BETWEEN 1 AND 20
     ) AS temp;