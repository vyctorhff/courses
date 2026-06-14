import unittest

from src.model.domain.user import User
from src.model.domain.transaction import Transaction
from src.model.repository.transaction_in_memory_repository import TransactionInMemoryRepository
from src.model.services.stament_service import Statement, StatementService

class StatementServiceTest(unittest.TestCase):

    def setUp(self):
        pass

    def test_should_find_by_email(self):
        repository = TransactionInMemoryRepository()
        service = StatementService(repository)

        user = User('name', 'email', 200)
        trans = Transaction(10, user, user)
        
        repository.add(trans)

        statement = service.find_transacations_by_user(user)

        self.assertEqual('email', statement.user.email)
        self.assertTrue(len(statement.list_transcation) > 0)
