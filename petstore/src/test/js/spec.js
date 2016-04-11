describe('test', function(){
	it('should have a title', function(){
		browser.get('http://127.0.0.1:8090/app/app.html#/');
		expect(browser.getTitle()).toEqual('Pet Store');
	});
});