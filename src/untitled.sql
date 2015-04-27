select A, B
from (select paymentDate as A, sum(amount) as B from payments
		group by paymentDate) as C
		order by B
		limit 1;