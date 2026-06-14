#import unittest
from unittest import TestCase, mock

from src.model.exceptions.exceptions import UserExistsError, UserInvalidError
from src.model.domain.user import User
from src.model.repository.user_in_memory_repository import UserInMemoryRepository
from src.model.services.new_user_service import NewUserService

class NewUserServiceTest(TestCase):

    def setUp(self):
        self.repo_mock = mock.Mock(spec=UserInMemoryRepository)
        self.service = NewUserService(self.repo_mock)

        self.user = User('name', 'email', 100.0)

    def test_new_user_successfully(self):
        self.repo_mock.exists_by_email.return_value = False

        self.service.add(self.user)
        self.repo_mock.add.assert_called_once()

    def test_new_user_that_exists(self):
        self.repo_mock.exists_by_email.return_value = True

        with self.assertRaises(UserExistsError):
            self.service.add(self.user)

        self.repo_mock.add.assert_not_called()
    
    def test_new_user_invalide(self):
        self.user.email = ''
        with self.assertRaises(UserInvalidError):
            self.service.add(self.user)
        
        self.repo_mock.add.assert_not_called()
