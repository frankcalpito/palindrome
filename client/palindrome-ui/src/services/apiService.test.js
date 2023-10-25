import apiService from './apiService';

describe('apiService', () => {
  it('should compose a valid request URL', () => {
    const endpoint = 'test-endpoint';

    // call the function and store the result
    const url = apiService.composeRequestURL(endpoint);

    expect(url).toBe(`${process.env.REACT_APP_API_URL}/${endpoint}`);
  });
});
