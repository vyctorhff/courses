
class StatementController:

    def __init__(self, find_user_service, service, view, select_user_controlller):
        self.service = service
        # self.find_user_service = find_user_service
        self.view = view
        self.select_user_controlller = select_user_controlller

    def process(self):
        self.view.print_main()
        self.select_user_controlller.process()

        self.select_user_controlller.print_select_user()
        user = self.select_user_controlller.get_user()

        stament = self.service.find_transacations_by_user(user)
        self.view.print_transaction(user, stament.list_transcation)
    
    def get_description(self):
        return 'Stament(Extrato)'
