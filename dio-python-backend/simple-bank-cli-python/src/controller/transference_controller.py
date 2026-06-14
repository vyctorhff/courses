from src.model.domain.transaction import Transaction
from src.view.messages import TRANSFERENCE_MAIN

class TransferenceController:

    def __init__(self, withdraw_service, select_user_controller, view):
        self.select_user_controller = select_user_controller
        self.service = withdraw_service
        self.view = view

    def process(self):
        print(TRANSFERENCE_MAIN)
        self.select_user_controller.process()

        user_source, user_target = self.set_users()
        value = float(input('Value: '))

        transaction = Transaction(value, user_source, user_target)
        self.service.add(transaction)

    def set_users(self):
        self.select_user_controller.print_select_user_custom('Select user source: ')
        user_source = self.select_user_controller.get_user()

        self.select_user_controller.print_select_user_custom('Select user target: ')
        user_target = self.select_user_controller.get_user()

        return user_source, user_target

    def get_description(self):
        return 'Transference'

class WithdrawMoneyView:
    
    def __init__(self):
        pass
